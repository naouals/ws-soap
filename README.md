# **Projet WS-SOAP : Service Bancaire avec JAX-WS**

## **Table des Matières**
1. [Description du Projet](#description-du-projet)  
2. [Structure du Projet](#structure-du-projet)  
3. [Fonctionnalités](#fonctionnalités)  
4. [Configuration Technique](#configuration-technique)  
5. [Déploiement et Utilisation](#déploiement-et-utilisation)  
6. [Exemples de Requêtes](#exemples-de-requêtes)  

---

## **Description du Projet**  
Ce projet implémente un **service web SOAP** (JAX-WS) pour une application bancaire simulée, avec :  
- Conversion de devises (EUR → DH)  
- Gestion de comptes bancaires (création, consultation)  
- Architecture basée sur **Java 17** et **Spring Boot**  

---

## **Structure du Projet**  
```
ws-soap/
├── src/
│   ├── main/
│   │   ├── java/ws/
│   │   │   ├── BanqueService.java   # Implémentation du service SOAP
│   │   │   ├── Compte.java         # Modèle de données
│   │   │   └── ServerJWS.java      # Point d'entrée du serveur
│   │   └── resources/
│   └── test/
├── client-soap-java/                # Client de test
├── pom.xml                          # Configuration Maven
└── target/                          # Fichiers compilés
```

---

## **Fonctionnalités**  
### **1. Service Bancaire (SOAP)**  
- **`conservationEuroToDH(double mt)`**  
  Convertit un montant EUR en DH (taux fixe: 1 EUR = 11 DH).  
  ```java
  @WebMethod(operationName = "ConservationEuroToDH")
  public double conservation(@WebParam(name="montant") double mt) {
      return mt * 11;
  }
  ```

- **`getCompte(int code)`**  
  Retourne un compte bancaire simulé avec :  
  - Code aléatoire  
  - Solde entre 0 et 60 000 DH  
  - Date de création  
  ```java
  @WebMethod
  public Compte getCompte(@WebParam(name = "code") int code) {
      return new Compte(code, Math.random() * 60000, new Date());
  }
  ```

- **`getComptes()`**  
  Liste de 3 comptes simulés.  

---

## **Configuration Technique**  
### **Dépendances (pom.xml)**  
```xml
<dependencies>
    <!-- JAX-WS (SOAP) -->
    <dependency>
        <groupId>com.sun.xml.ws</groupId>
        <artifactId>jaxws-ri</artifactId>
        <version>4.0.2</version>
    </dependency>
    <!-- Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
</dependencies>
```

### **Paramètres Clés**  
- **Port du serveur** : `9090`  
- **Endpoint** : `http://0.0.0.0:9090/BanqueWS`  

---

## **Déploiement et Utilisation**  
### **1. Lancer le Serveur**  
Exécutez `ServerJWS.java` :  
```java
public static void main(String[] args) {
    Endpoint.publish("http://0.0.0.0:9090/", new BanqueService());
    System.out.println("Service déployé sur : http://localhost:9090/");
}
```

### **2. Tester avec un Client SOAP**  
Exemple de client Java (`client-soap-java`) :  
```java
BanqueService proxy = new BanqueWS().getBanqueServicePort();
System.out.println("40 EUR = " + proxy.conservationEuroToDH(40) + " DH");
```

  ![image](https://github.com/user-attachments/assets/e6429a51-924a-443d-be35-b003173e34e9)

### **3. Accéder au WSDL**  
Ouvrez dans un navigateur :  
```
http://localhost:9090/BanqueWS?wsdl
```

---

## **Exemples de Requêtes**  
### **Requête SOAP (Conversion EUR → DH)**  
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws/">
   <soapenv:Header/>
   <soapenv:Body>
      <ws:ConservationEuroToDH>
         <montant>40</montant>
      </ws:ConservationEuroToDH>
   </soapenv:Body>
</soapenv:Envelope>
```
### **Réponse**  
```xml
<ns2:ConservationEuroToDHResponse xmlns:ns2="http://ws/">
   <return>440.0</return>
</ns2:ConservationEuroToDHResponse>
```
![image](https://github.com/user-attachments/assets/90e6bfe7-df42-4661-a3fb-cef4a773d0c4)

### **Requête SOAP (getCompte)**  

![image](https://github.com/user-attachments/assets/a11c9261-9f6a-4785-b0cf-c6e499a9a5cc)

### **Requête SOAP (getComptes)**  

![image](https://github.com/user-attachments/assets/8a58d538-1743-4957-84e5-94ecda4975a3)



---

## **Captures d'Écran**  
1. **Console du Serveur**  
   ![image](https://github.com/user-attachments/assets/11c914fc-a0f4-4119-bbbe-e7c0769fbabc)


2. **WSDL Généré**  
   ![image](https://github.com/user-attachments/assets/48fa1190-52a9-40ca-a69f-4f616bf63c84)

---

