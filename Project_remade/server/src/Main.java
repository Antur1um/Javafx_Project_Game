import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.util.ArrayList;



public class Main {
    public static void main (String[]args) {
        Info info = Info.getInstance();
        new NewThread(1, 5001);
        new NewThread(2, 5002);
    }
}

class Network {
    private Socket s;
    private ServerSocket ss;
    private InputStream in;
    private OutputStream out;
    private int answerInt;
    private String answerString;
    private int status;

    private String curLogin;

    public Network() {
        status = 0;
    }

    private void inReadInt() {
        try {
            byte[] buf = new byte[2000];
            int count = in.read(buf);
            ByteBuffer bb = ByteBuffer.wrap(buf, 0, count);
            answerInt = bb.getInt();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                in.close();
                out.close();
                s.close();
                System.out.println("Close");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void outWriteInt(int res) {
        try {
            byte[] buf = new byte[1000];
            buf = ByteBuffer.allocate(4).putInt(res).array();
            out.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                in.close();
                out.close();
                s.close();
                System.out.println("Close");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void inReadString() {
        try {
            byte[] buf = new byte[2000];
            int count = in.read(buf);
            answerString = new String(buf, 0, count);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                in.close();
                out.close();
                s.close();
                System.out.println("Close");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void outWriteString(String res) {
        try {
            out.write(res.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                in.close();
                out.close();
                s.close();
                System.out.println("Close");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void work(int socket) {
        try {
            ss = new ServerSocket(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                System.out.println("Waiting connection...");
                s = ss.accept();
                System.out.println("Local port: " + s.getLocalPort());
                System.out.println("Remote port: " + s.getPort());
                in = s.getInputStream();
                out = s.getOutputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Info {
    public String str1;
    public String str2;
    private static Info instance;

    private Info() {
        str1 = "-1";
        str2 = "-1";
    }

    public static Info getInstance() {
        if(instance == null) {
            instance = new Info();
        }
        return instance;
    }

    public String getInfo(String name) {
        if (name.equals("Поток1")) {
            String buf = str2;
            str2 = "-1";
            return buf;
        }
        else if (name.equals("Поток2")) {
            String buf = str1;
            str1 = "-1";
            return buf;
        }
        return "-1";
    }

    public void sendInfo(String name, String str_p) {
        if (name.equals("Поток1")) {
            if (str1.equals("-1")) str1 = str_p;
            else str1 += '#' + str_p;
        }
        else if (name.equals("Поток2")) {
            if (str2.equals("-1")) str2 = str_p;
            else str2 += '#' + str_p;
        }
    }
}

class NewThread implements Runnable {
    Thread t;
    int socket;
    int id;
    NewThread(int id_p, int socket_p) {
        id = id_p;
        socket = socket_p;
        t = new Thread(this, "Поток" + id);
        System.out.println("Поток создан: " + t);
        t.start();
    }

    public void run() {
        Network network = new Network();
        network.work(socket);
    }
}