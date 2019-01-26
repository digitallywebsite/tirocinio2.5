package gestioneutente.model;

/**
 * Time è una classe con 3 metodi, essa permette di 
 * calcolare quanto tempo manca tra un orario di fine - un orario di inizio.
 *
 */
public class Time {

  /**
   * Il metodo CalcTime calcola la differenza tra endTime - startTime.
   * @param startTime tipo Stringa, variabile che contiene una data di inizio
   * @param endTime tipo Stringa, variabile che contiene una data di fine
   * @return time tipo Stringa, variabile che contiente il risultato di endTime - startTime
  */
  public String calcTime(long startTime, long endTime) {
    String time = "";

    long seconds = (endTime / 1000) - (startTime / 1000);

    if (seconds > 0 && seconds <= 59) {
      if (seconds == 1) {
        time = "1 secondo";
      } else {
        time = seconds + " secondi";
      }
    } else {
      if (seconds >= 60 && seconds <= 3599) {
        long minute = seconds / 60;
        if (minute == 1) {
          time = "1 minuto";
        } else {
          if (minute > 1) {
            time = minute + " minuti ";
          }
        }
      } else {
        if (seconds >= 3600 && seconds <= 86399) {
          long hour = seconds / 3600;
          long minute = (seconds / 60) - (hour * 60);

          if (hour == 1) {
            if (minute > 0) {
              time = "1 ora e " + minute + " minuti";
            } else {
              time = "1 ora";
            }
          } else {
            if (hour > 1) {
              if (minute > 0) {
                time = hour + " ore e " + minute + " minuti";
              } else {
                time = hour + " ore";
              }
            }
          }
        }
      }
    }
    return time;
  }

  /**
   * Il metodo CalcHour permette di calcolare l'ora rimanente tra endTime - startTime.
   * @param startTime tipo Stringa, variabile che contiene una data di inizio
   * @param endTime tipo Stringa, variabile che contiene una data di fine
   * @return hour tipo long, variabile che contiene la differenza tra endTime - startTime
  */
  public long calcHour(long startTime, long endTime) {
    long hour = 0;

    long seconds = (endTime / 1000) - (startTime / 1000);

    if (seconds >= 3600 && seconds <= 86399) {
      hour = seconds / 3600;
    }

    return hour;
  }

  /**
   * Il metodo CalcMinute permette di calcolare minuti rimanenti tra endTime - startTime - hour.
   * @param startTime tipo Stringa, variabile che contiene una data di inizio
   * @param endTime tipo Stringa, variabile che contiene una data di fine
   * @param hour tipo long, variabile che contiene la differenza tra endTime - startTime
   * @return minute tipo long, variabile che contiene la differenza tra endTime - startTime - hour
  */
  public long calcMinute(long startTime, long endTime, long hour) {
    long minute = 0;
    
    long seconds = (endTime / 1000) - (startTime / 1000);
    
    if (seconds >= 60 && seconds <= 86399) {
      minute = (seconds / 60) - (hour * 60);         
    }
    
    return minute;
  }
}
