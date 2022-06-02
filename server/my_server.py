import socket
# import main
from threading import Thread
import pandas as pd
pd.options.mode.chained_assignment = None

HOST = "192.168.1.5"
PORT = 15000


def decode_button_no(button_no):
    if button_no == b'\x01':
        return 1
    if button_no == b'\x02':
        return 2
    if button_no == b'\x03':
        return 3
    if button_no == b'\x04':
        return 4
    if button_no == b'\x05':
        return 5

def what_request(conn):
    conn.settimeout(0.1)
    request = b''
    try:
        request = conn.recv(1)
        if request == b"":
            print("empty")
    except socket.timeout:
        print("timeout")
    conn.settimeout(None)
    return request


def register_credentials(conn):
    conn.settimeout(0.1)
    login = b""
    password = b""
    try:
        c = conn.recv(4096)
        login, password = c.split(b"|")
    except socket.timeout:
        print("timeout")
    users = pd.read_csv("users_database.csv", usecols=["login", "password"])
    creds = pd.Series([login.decode("utf-8"), password.decode("utf-8")], index=["login", "password"])
    doesnt_exist = True
    for i in users.transpose():
        if (users.transpose()[i] == creds).all():
            doesnt_exist = False
    if (doesnt_exist):
        users = users.append(creds.to_frame().transpose(), ignore_index=True)
    users.to_csv("users_database.csv")
    conn.settimeout(None)


def log_in_credentials(conn):
    conn.settimeout(0.1)
    login = b""
    password = b""
    try:
        c = conn.recv(4096)
        login, password = c.split(b"|")
    except socket.timeout:
        print("timeout")
    users = pd.read_csv("users_database.csv", usecols=["login", "password"])
    creds = pd.Series([login.decode("utf-8"), password.decode("utf-8")], index=["login", "password"])
    print(creds)
    ret = b"0"
    for i in users.transpose():
        if (users.transpose()[i] == creds).all():
            ret = b"1"
    conn.sendall(ret)
    conn.settimeout(None)


def recieve_jpg(conn):
    conn.settimeout(0.1)
    credentials = b""
    try:
        credentials = conn.recv(40)
    except socket.timeout:
        print(credentials)
        print("error")
    login = credentials.split(b"|")[0].decode()
    password = credentials.split(b"|")[1].strip().decode()
    fp = open("board.jpg", "wb")
    while True:
        try:
            data = conn.recv(4096)
            if data == b"":
                break
        except socket.timeout:
            break
        fp.write(data)
    fp.close()
    # fen = main.get_fen("board.jpg")
    fen = "rnbqk2r/pp3pbp/3p1np1/2pPP3/5P2/2N5/PP4PP/R1BQKBNR"
    # fen = "rn1q1rk1/ppp1ppbp/5np1/8/2QPP1b1/2N2N2/PP3PPP/R1B1KB1R"
    # fen = "r1bqk2r/ppp2ppp/2n1pn2/3p4/2PP4/P1Q5/1P2PPPP/R1B1KBNR"
    # fen = "rnbq1rk1/ppp2pbp/3p1np1/3Pp3/2P1P3/2N1BP2/PP4PP/R2QKBNR"
    # fen = "rnbqkbnr/pppp1ppp/8/8/3pP3/8/PPP2PPP/RNBQKBNR"
    debutes = pd.read_csv("debutes_database.csv", usecols=["eco_num", "name", "moves", "FEN"])
    debute = ""
    s = pd.Series(debutes["FEN"]).str.contains(fen, regex=False)
    i = s[s].index.values
    if len(i) != 0:
        debute = debutes['name'][i[0]]
    data = {"FEN": fen, "name": debute}
    users_games = pd.read_csv("users_games.csv", usecols=["login", "password", "game1", "game1_name", "game2",
                                                          "game2_name", "game3", "game3_name", "game4", "game4_name",
                                                          "game5", "game5_name"])
    users = users_games[["login", "password"]]
    creds = pd.Series([login, password], index=["login", "password"])
    for i in users.transpose():
        if (users.transpose()[i] == creds).all():
            for j in range(5, 1, -1):
                users_games["game" + str(j)][i] = users_games["game" + str(j - 1)][i]
                users_games["game" + str(j) + "_name"][i] = users_games["game" + str(j - 1) + "_name"][i]
            users_games["game1"][i] = fen
            users_games["game1_name"][i] = debute
    print(users_games.to_string())
    users_games.to_csv('users_games.csv', index=False)
    conn.settimeout(None)


def send_game(conn):
    conn.settimeout(0.1)
    fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"
    name = "Начало партии"
    button_no = b''
    try:
        button_no = conn.recv(1)
        if button_no == b'':
            print("empty")
    except socket.timeout:
        print("timeout")
    button_no = decode_button_no(button_no)
    credentials = b""
    try:
        credentials = conn.recv(40)
    except socket.timeout:
        print(credentials)
        print("error")
    login = credentials.split(b"|")[0].decode()
    password = credentials.split(b"|")[1].strip().decode()
    users_games = pd.read_csv("users_games.csv", usecols=["login", "password", "game1", "game1_name", "game2",
                                                          "game2_name", "game3", "game3_name", "game4", "game4_name",
                                                          "game5", "game5_name"])
    users = users_games[["login", "password"]]
    creds = pd.Series([login, password], index=["login", "password"])
    index = 0
    for i in users.transpose():
        if (users.transpose()[i] == creds).all():
            index = i
    fent = users_games["game" + str(button_no)][index]
    namet = users_games["game" + str(button_no) + "_name"][index]
    if fent == fent:
        fen = fent
    if namet == namet:
        name = namet
    data = fen + "," + name
    conn.sendall(bytes(data, encoding="utf-8"))
    conn.settimeout(None)


def serve_conn(socket):
    conn, addr = socket.accept()
    with conn:
        print(f"Connected by {addr}")
        request = what_request(conn)
        if request == b'\x03':
            print("send game")
            send_game(conn)
        if request == b'\x02':
            print("scanning")
            recieve_jpg(conn)
        if request == b'\x01':
            print("credentials")
            log_in_credentials(conn)
        if request == b'\x00':
            print("register")
            register_credentials(conn)
        print("Done!")


with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    s.bind((HOST, PORT))
    s.listen(1)
    # обработка подключений
    while True:
        Thread(serve_conn(s)).start()
