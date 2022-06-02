import json
import os

from flask import Flask, redirect, url_for, request, Response
from flask_cors import CORS
import pymysql

app = Flask(__name__)
CORS(app, support_credentials=True)
app.config["db"] = pymysql.connect(host="106.15.6.161", port=3306,
                                   user="game_platform", password="root", database="game_platform")
app.config["db2"] = pymysql.connect(host="106.15.6.161", port=3306,
                                   user="game_platform", password="root", database="game_platform",
                                   cursorclass=pymysql.cursors.DictCursor)


@app.route('/game', methods=['POST', 'GET'])
def getGame():
    game_name = request.form['name']
    sql = "select * from game where name='%s'" % game_name
    cursor = app.config["db"].cursor()
    cursor.execute(sql)
    app.config["db"].commit()
    result = cursor.fetchall()
    game = {
        "name": result[0][0],
        "describe": result[0][1],
        "type": result[0][2],
        "rating": result[0][3],
        "cover": result[0][4],
        "icon": result[0][5],
    }
    game = json.dumps(game, indent=4, ensure_ascii=False)
    return game


@app.route('/allGame', methods=['GET', 'POST'])
def getAllGame():
    sql = "select * from game where available=1"
    cursor = app.config["db"].cursor()
    cursor.execute(sql)
    app.config["db"].commit()
    result = cursor.fetchall()
    games = {}
    for i in range(4):
        game = {
            "name": result[i][0],
            "describe": result[i][1],
            "type": result[i][2],
            "rating": result[i][3],
            "cover": result[0][4],
            "icon": result[0][5],
        }
        # game = json.dumps(game, indent=4, ensure_ascii=False)
        games[str(i)] = game
    return games


@app.route('/getImage/<file_name>', methods=['GET'])
def get_image(file_name):
    file_path = os.path.join('./gameImage/' + file_name + '.jpg')
    with open(file_path, 'rb') as img:
        img = img.read()
        res = Response(img, mimetype="image/jpg")
        return res


@app.route('/gamedetail/<name>', methods=['POST', 'GET'])
def getAGame(name):
    game_name = name
    # game_name = request.form['name']
    sql = "select * from game_detail where name='%s'" % game_name
    cursor = app.config["db"].cursor()
    cursor.execute(sql)
    app.config["db"].commit()
    result = cursor.fetchall()
    game = {
        "name": result[0][1],
        "type": result[0][2],
        "recommend":result[0][3],
        "downloadnum":result[0][4],
        "downloadsize":result[0][5],
        "concernnum":result[0][6],
        "score":result[0][7],
        "brief":result[0][8],
        "gamemainimage":result[0][9],
        "gamedetailimage1":result[0][10],
        "gamedetailimage2": result[0][11],
        "gamedetailimage3": result[0][12],
    }
    game = json.dumps(game, indent=4, ensure_ascii=False)

    sql = "select * from comment where commentgame='%s'" % game_name
    cursor = app.config["db"].cursor()
    cursor.execute(sql)
    app.config["db"].commit()
    result = cursor.fetchall()
    comments = {}
    num = 0
    for x in result:
        num += 1
    for i in range(num):
        comment = {
            "commentid": result[i][0],
            "gamename": result[i][1],
            "content": result[i][2],
            "username": result[i][3],
            "usericon": result[i][4],
            "commenttime": result[i][5]
        }
        comments[str(i)] = comment

    lastresult = {}
    lastresult["gamedetail"] = game
    lastresult["comment"] = comments
    return lastresult

@app.route('/games', methods=['GET'])
def getGames():
    sql = "select * from game order by rating desc"
    cursor = app.config["db2"].cursor()
    cursor.execute(sql)

    result = cursor.fetchall()
    game = json.dumps(result, indent=4, ensure_ascii=False)
    return game

if __name__ == '__main__':
    app.run(host='127.0.0.1', port=5000)
