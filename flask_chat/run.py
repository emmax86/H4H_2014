from chat import app
from gevent import monkey
from socketio.server import SocketIOServer


monkey.patch_all()

PORT = 5000

if __name__ == '__main__':
    print 'Listening on http://stevex86.com:%s and on port 10843 (flash policy server)' % PORT
    SocketIOServer(('0.0.0.0', PORT), app, resource="socket.io").serve_forever()
