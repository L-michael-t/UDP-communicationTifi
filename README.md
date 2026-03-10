# UDP Client-Server in Java

Questo progetto mostra un semplice esempio di comunicazione **Client-Server tramite protocollo UDP** usando Java.
Il client invia un messaggio al server e il server risponde confermando la ricezione.

---

## 📁 Struttura del progetto

 MainClient.java
 MainServer.java

* **MainClient.java** invia un messaggio al server e riceve una risposta
* **MainServer.java** riceve il messaggio dal client e invia una risposta

---

## ⚙️ Come funziona

### Server

Il server:

1. Apre una socket UDP sulla porta **3989**
2. Attende un messaggio dal client
3. Legge il messaggio ricevuto
4. Invia una risposta al client

### Client

Il client:

1. Crea una socket UDP
2. Invia un messaggio al server
3. Attende la risposta
4. Stampa la risposta ricevuta

---

## ▶️ Come eseguire il programma

### 1. Compilare i file

```bash
javac MainServer.java
javac MainClient.java
```

### 2. Avviare il server

```bash
java MainServer
```

Output:

```
il server attende
```

### 3. Avviare il client

```bash
java MainClient
```

---

## 💻 Esempio di output

### Server

```
il server attende
ricevuto: bella a tutti ragzzi
```

### Client

```
risposta dal server: ho ricevuto: bella a tutti ragzzi
```

---

## 🧰 Tecnologie utilizzate

* Java
* UDP (User Datagram Protocol)
* DatagramSocket
* DatagramPacket
* InetAddress

---

## 🚀 Possibili miglioramenti

* Supporto per più client contemporaneamente
* Server sempre in ascolto con ciclo `while`
* Migliore gestione degli errori
* Sistema di messaggi più avanzato
