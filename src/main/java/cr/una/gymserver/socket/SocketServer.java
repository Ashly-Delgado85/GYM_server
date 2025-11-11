package cr.una.gymserver.socket;

import cr.una.gymserver.repository.GymClassRepo;
import cr.una.gymserver.repository.ReservationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Component @RequiredArgsConstructor
public class SocketServer implements CommandLineRunner {
  private final ReservationRepo reservationRepo;
  private final GymClassRepo classRepo;

  @Override public void run(String... args) throws Exception {
    ServerSocket server = new ServerSocket(5050);
    Thread t = new Thread(() -> {
      while (true) {
        try {
          Socket s = server.accept();
          new Thread(() -> handle(s)).start();
        } catch (IOException ignored) {}
      }
    });
    t.setDaemon(true); t.start();
    System.out.println("Socket server listening on 5050");
  }

  private void handle(Socket s) {
    try (s;
         BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
         PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {
      String line;
      while ((line = in.readLine()) != null) {
        if (line.startsWith("CUPOS")) {
          Long clsId = Long.parseLong(line.split(" ")[1]);
          var cls = classRepo.findById(clsId).orElse(null);
          if (cls == null) { out.println("ERR:CLS_NOT_FOUND"); continue; }
          long taken = reservationRepo.countByClsIdAndRsvStatus(clsId, "CONFIRMED");
          int disp = cls.getCapacity() - (int)taken;
          out.println("DISPONIBLES=" + disp);
        } else if ("PING".equalsIgnoreCase(line)) out.println("PONG");
        else out.println("ERR");
      }
    } catch (Exception ignored) {}
  }
}
