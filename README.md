# QuizMaster
> Quizanwendung entwickelt im Rahmen des Moduls "Software Qualitätssicherung" an der TH Rosenheim<br>
> Sommersemester 2023<br>
> Franziska Greiner

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=FranziskaGreiner_QuizMaster&metric=bugs)](https://sonarcloud.io/summary/new_code?id=FranziskaGreiner_QuizMaster)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=FranziskaGreiner_QuizMaster&metric=coverage)](https://sonarcloud.io/summary/new_code?id=FranziskaGreiner_QuizMaster)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=FranziskaGreiner_QuizMaster&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=FranziskaGreiner_QuizMaster)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=FranziskaGreiner_QuizMaster&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=FranziskaGreiner_QuizMaster)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=FranziskaGreiner_QuizMaster&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=FranziskaGreiner_QuizMaster)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=FranziskaGreiner_QuizMaster&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=FranziskaGreiner_QuizMaster)
## Projektdokumentation
**Inhaltsverzeichnis**
1. [Einführung und Ziele](#1-einführung-und-ziele)
2. [Architektur-Einschränkungen](#2-architektur-einschränkungen)
3. [Umfang und Kontext des Systems](#3-umfang-und-kontext-des-systems)
4. [Lösungsstrategie](#4-lösungsstrategie)
5. [Bausteinsicht](#5-baustein-sicht)
6. [Laufzeit-Sicht](#6-laufzeit-sicht)
7. [Deployment-Sicht](#7-deployment-sicht)
8. [Übergreifende Konzepte](#8-übergreifende-konzepte)
9. [Architektur-Entscheidungen](#9-architektur-entscheidungen)
10. [Qualität](#10-qualität)
11. [Risiken und technische Schulden](#11-risiken-und-technische-schulden)

___

**Lokale Ausführung der Anwendung**
- Konfiguration
  - Umgebungsvariablen setzen: *DB_PASSWORD="<datenbank-passwort>", API_KEY="<api-key>"*
  - Evtl. Port-Anpassung: per Default Datenbank Port 5432, Backend Port 8080, Frontend Port 4200
- Installation
  - Datenbank: *docker-compose --up*
  - Backend: *mvn clean install*
  - Frontend: *npm i*
- Starten der Anwendung
  - Backend: *mvn spring-boot:run*
  - Frontend: *npm start*, im Ordner ./frontend/quizmaster-frontend
- Zugriff auf die Anwendung
  - Backend: *http://localhost:8080/*
  - Frontend: *http://localhost:4200/*
- Testen
  - Backend (standard): *mvn test*
  - Frontend (standard): *npm test*, im Ordner ./frontend/quizmaster-frontend
  - E2E-Test: *npx cypress run*, im Ordner ./frontend/quizmaster-frontend
  - Last-Test: *k6 run load-test.js*, im Ordner ./src/test/performance
  - ESLint: *npx eslint . --ignore-pattern 'dist/**'*, im Ordner ./frontend/quizmaster-frontend
___

# 1. Einführung und Ziele

## Fachliche Anforderungen
QuizMaster ist eine Quizspiel-Anwendung, bei der der Nutzer Fragen und dazugehörige Antwortmöglichkeiten erhält.
Er kann sich für eine Antwort entscheiden und sieht dann, ob diese der richtigen entspricht.
Klickt der User auf den Button zum Starten des Quiz, wird die externe API für 10 Fragen angefragt und diese werden in der angezeigt.
Zudem werden immer um Mitternacht 10 neue Fragen in der Datenbank als Fallback gespeichert.

## Qualitätsziele
Die Qualitätsziele, die für die Anwendung am wichtigsten sind, sind:
- Security
- Usability
- Functional Suitability
- Maintainability
- Reliability

Siehe [Kapitel 10](#kapitel-10-qualität) für eine ausführliche Auflistung der Qualitätsziele.

## Stakeholder
Das Projekt wird im Rahmen einer Studienarbeit an der Technischen Hochschule Rosenheim umgesetzt.
Die Stakeholder sind deshalb begrenzt auf den Dozenten und die Studenten des Moduls "Software Qualitätssicherung".

# 2. Architektur-Einschränkungen
- **Vorgabe**: Die Grundstruktur der Anwendung ist durch die Vorgaben des Dozenten festgelegt.
Die Anwendung wird demnach als Microservice Anwendung mit folgenden Bestandteilen umgesetzt:
  - Frontend
  - Backend
  - Datenbank
  - Externe REST-API

- **Technologische Einschränkungen**:
Der Rahmen des Projekts gibt vor, ausschließlich Open-Source-Tools und Software zu verwenden.
- **Zeitliche Einschränkungen**:
Dadurch dass es sich um eine Studienarbeit handelt, steht eine sehr begrenzte Zeit zur Verfügung.
Die Architektur wurde aus diesem Grund als einfach und schnell umsetzbar gewählt.
- **Skills**: Die Anwendung wird von einer Entwicklerin alleine umgesetzt, wodurch die Wahl von Programmiersprachen, Frameworks und Tools teilweise aufgrund des Vorwissens der Entwicklerin getroffen wurden.

# 3. Umfang und Kontext des Systems
Um Umfang und Kontext des Systems zu veranschaulichen, wird dem [C4 Modell](https://c4model.com/) für die Softwareentwicklung gefolgt.

**System Context Diagram**

Das System Context Diagramm gibt einen Gesamtüberblick über das System und ordnet es in den Kontext und den Anwendungsbereich, in dem aus auftritt, ein.
Hier geht es noch nicht um Details oder verwendete Technologie, sondern mehr darum, das Große und Ganze zu sehen.

![SystemContext](diagrams/SystemContextDiagram.drawio.png)

**Container Diagram**

Im Container Diagram wird genauer auf das System und seine Bestandteile eingegangen.
Die verschiedenen Container, als separat lauffähige Einheiten sind für dieses Projekt das Angular Frontend, das Java Backend und die PostgreSQL Datenbank. 
Es wird gezeigt, wie die einzelnen Container miteinander kommunizieren und welche Aufgaben sie haben.

![Container](diagrams/ContainerDiagram.drawio.png)

**Component Diagram**

Das Komponenten Diagram gibt einen detaillierteren Überblick über die einzelnen Komponenten der Container und zeigt seinen Aufbau.
Da der Umfang des Projekts sehr klein ist, wurde hier lediglich das Backend als Hauptkomponente zur genaueren Betrachtung gewählt.

![Component](diagrams/ComponentDiagram.drawio.png)

# 4. Lösungsstrategie
**Allgemeine Strategie**

Die Anwendung folgt einer typischen Webanwendungsarchitektur mit Frontend und Backend.
Das Backend ist für die Verarbeitung von Businesslogik, Verwaltung von Datenbankoperationen und Verarbeitung der Daten aus der externen API zuständig.
Das Frontend ist für die Präsentation der Daten für den Benutzer und die Verwaltung der Benutzerinteraktionen verantwortlich.

**Technische Entscheidungen**
![TechStack](diagrams/TechStack.png)

Die Anwendung wird mit folgendem Technologiestack umgesetzt:
- Frontend: Angular
- Backend: Java
- Datenbank: PostgreSQL
- Externe API: https://quizapi.io/

Die Entscheidungen für diesen Technologie-Stack wurde aufgrund verschiedener Faktoren getroffen:

<ins>Frontend: Angular
- Modularität und Skalierbarkeit: Angular ist ein Framework, das auf Komponenten basiert und so eine klare Trennung von Verantwortlichkeiten in der Anwendung erlaubt. Diese modulare Struktur hilft bei der Organisation des Codes, fördert die Wiederverwendbarkeit von Code und macht das Projekt skalierbar.
- Community und Support: Angular hat eine große Community und wird von Google unterstützt, was die Lösung von Problemen und die Suche nach Informationen erleichtert.

<ins>Backend: Java
- Stabilität und Zuverlässigkeit: Java ist eine bewährte, robuste Sprache, die für ihre Stabilität und Zuverlässigkeit bekannt ist.
- Spring Framework: Das Spring Framework bietet eine Menge Funktionen, eine schnelle Einrichtung und eine Reihe von "Starter" -Paketen, um schnell eine Anwendung mit verschiedenen Technologien aufzusetzen

<ins>Datenbank: PostgreSQL
- Open Source und Kosteneffizienz: PostgreSQL ist eine leistungsfähige, Open-Source-Objekt-relationale Datenbank. Es ist eine kostengünstige Lösung, insbesondere für kleinere Systeme, da es ohne zusätzliche Lizenzkosten eingesetzt werden kann.
- Leichtgewicht und Einfachheit: PostgreSQL ist relativ einfach einzurichten und zu verwalten, was es ideal für kleinere Systeme macht, die eine zuverlässige, aber unkomplizierte Datenbanklösung benötigen.

<ins>Anbindung externe API: RESTful
- Die Anbindung an die externe API RESTful umzusetzen war vorgegeben, diese Vorgehensweise ist aufgrund ihrer Einfachheit, Interoperabilität und Zustandslosigkeit weit verbreitet.
- Reichhaltige Datenquelle: Die Verwendung einer externen API wie quizapi.io ermöglicht den Zugriff auf eine breite Palette von Fragen und Antworten, die ständig aktualisiert und erweitert werden. Dadurch wird die Notwendigkeit, eigene Fragen zu erstellen und zu verwalten, vermieden und gleichzeitig die Vielfalt und Aktualität der in der Anwendung präsentierten Quizfragen sichergestellt.
- Einfache Integration: Quizapi.io bietet eine gut dokumentierte und einfach zu verwendende API. Dadurch wird der Aufwand für die Integration und Verwaltung der Datenkommunikation erheblich reduziert.
- Skalierbarkeit: Da die Datenverarbeitung auf der Seite von quizapi.io stattfindet, reduziert dies den Datenverarbeitungsbedarf auf Seiten der Anwendung und ermöglicht eine höhere Skalierbarkeit.

Für die Wahl der Frontend-, Backend- und Datenbank-Technologie war außerdem das Vorwissen der Entwicklerin entscheidend.
Durch die Nutzung dieser Technologien in vergangenen Projekten hat sich die Wahl zusätzlich angeboten.

# 5. Baustein-Sicht
In [Kapitel 3](#kapitel-3-qualität) wurde durch Anwenden des C4-Modells bereits drei Diagramme zur Darstellung der Anwendung aufgeführt.
Wichtige Bausteine und deren Beziehungen können dort entnommen werden.
Im Kontextdiagramm wird das Gesamtsystem als Blackbox dargestellt, als Whitebox wird es dann im Containerdiagramm genauer aufgegliedert und schließlich liefert das Komponentendiagramm eine Whitebox-Sicht auf eine Komponente der Anwendung, nämlich auf das Backend.
Aufgrund der geringen Größe und der Einfachheit des Systems ist eine weitere Darstellung nicht nötig.

# 6. Laufzeit-Sicht
Im Folgenden werden verschiedene Szenarien der Anwendung zur Laufzeit stichpunktartig erläutert.

**Start der Anwendung**

- Spring Boot Backend verbindet sich mit der PostgreSQL-Datenbank, die in einem Docker-Container läuft
- Angular Frontend wird von HTTP-Server zur Verfügung gestellt, wird initialisiert, lädt die erste Ansicht für den User

**Betrieb**
- User öffnet Anwendung in seinem Webbrowser und startet Quiz über Button
- Frontend kommuniziert mit Backend, um Quizfragen von der externen API abzurufen
- Wenn User auf Antwort klickt, sieht er durch farbliches Feedback, ob es die richtige war
- Endpunkt zum Abrufen der Quizfragen von der externen API, der auch vom Frontend genutzt wird: http://localhost:8080/quiz
- Endpunkt zum Erneuern der gespeicherten Fragen in der Datenbank: http://localhost:8080/question

**Herunterfahren der Anwendung**
- Backend trennt Verbindung zur Datenbank
- Frontend steht dem User nicht mehr zur Verfügung
- durch Stoppen des Docker-Containers für die Datenbank werden alle aktiven Verbindungen der PostgreSQL-Instanz beendet

# 7. Deployment-Sicht
Bei aktuellem Stand ist die Anwendung lediglich lokal ausführbar.
In Zukunft sollte die Anwendung auf verschiedenen Umgebungen (Deployment, Test, Production) deployt werden.

Um in Zukunft ein einfaches und schnelles Deployment zu ermöglichen und wichtige Schritte zu automatisieren, wurde eine Github Pipeline erstellt.

**Github Pipeline**

Ausgelöst bei jedem Push in das Repository
- *build*
  - JDK 17 und Node.js werden eingerichtet
  - Backend wird gebaut (Maven: Clean, Package)
  - Frontend wird gebaut (npm: Installieren der Abhängigkeiten, Build)
- *unit-and-integration-tests*
  - JDK 17 und Node.js werden eingerichtet
  - Backend-Tests werden ausgeführt (Maven: Tests)
  - Backend-Code-Coverage wird gemessen (Maven: Jacoco)
  - Frontend-Tests werden ausgeführt (npm: Test)
  - Frontend-Code-Coverage wird gemessen (npm: Test mit Code-Abdeckung)
- *sonar-backend-analysis*
  - JDK 17 und Maven werden eingerichtet
  - Cache für SonarCloud und Maven wird verwendet
  - Build und Analyse mit SonarCloud werden durchgeführt
- *eslint-frontend-analysis*
  - Node.js wird eingerichtet
  - Abhängigkeiten des Frontends werden installiert
  - Frontend wird gebaut
  - ESLint wird ausgeführt, um statische Code-Analyse durchzuführen
- *e2e-and-performance-tests*
  - JDK 17 wird eingerichtet
  - Cache für Maven wird verwendet
  - Backend und Frontend werden gestartet
  - E2E-Tests mit Cypress werden durchgeführt
  - k6 wird installiert und Lasttests werden ausgeführt


# 8. Übergreifende Konzepte
In diesem Punkt sollen wichtige übergreifende Konzepte angesprochen werden, die aufgrund des begrenzten Rahmens für dieses Projekt zu kurz kamen.
Diese könnten bei einer Weiterentwicklung der Anwendung besonders berücksichtigt werden.

- **Logging und Tracing**
  - Implementierung von verbesserten Logging-Mechanismen, um wichtige Ereignisse, Fehler und Debugging-Informationen zu protokollieren. Im Moment ist lediglich die Erstellung einer einfachen Logdatei möglich.
  - Verwendung von Tracing-Techniken, um den Fluss von Anfragen und Prozessen in der Anwendung zu verfolgen und Engpässe oder Leistungsprobleme zu identifizieren
- **Caching**: Verwendung von Caching, um häufig abgerufene Daten zu speichern und die Performance der Anwendung zu verbessern.
- **Internationalisierung und Lokalisierung**: Implementierung von Mehrsprachigkeit in der Anwendung, um Benutzer aus verschiedenen Ländern und Kulturen anzusprechen.
- **Error Handling und Exception Management**
  - Verhalten der Anwendung in Fehlerfällen verbessern
  - Abhängigkeiten der einzelnen Komponenten minimieren


# 9. Architektur-Entscheidungen
Die wichtigsten Architektur-Entscheidungen werden im Folgenden in Form von sogenannten *Architecture Decision Records* (ADR) erklärt.

ADRs enthalten neben Titel, Kontext, Entscheidung und Konsequenzen der Architekturentscheidungen normalerweise auch den Status.
Da dieser aber für alle der hier erwähnten Entscheidungen "Akzeptiert" lautet, wird er in den Tabellen nicht separat aufgeführt.

Stattdessen wurde als Extrapunkt "Begründung" mit aufgenommen, um die wichtigsten Gründe für die Entscheidung zu erwähnen.


|                | ADR 1: Backend Architektur-Pattern                                                                                                                                                                                        |
|----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| *Kontext*      | Die Entscheidung des Architektur-Patterns ist wichtig, um einen sinnvollen Aufbau des Backends zu gewährleisten. Es wird ein Entwurfsmuster benötigt, das Datenverarbeitung, Benutzeroberfläche und Kontrollfluss trennt. |
| *Entscheidung* | Model-View-Controller (MVC) Muster                     |
| *Begründung*   | MVC ist ein weit verbreitetes Muster und ist für Spring Boot und webbasierte Anwendungen gut geeignet. Es macht die Anwendung leichter verwaltbar und testbar.                                                                                                                                                                         |
| *Konsequenzen* | Durch die Entscheidung wird eine bestimmte Struktur der Anwendung erzwungen, die zwar die Komplexität leicht erhöht, aber die Wartbarkeit und Verständlichkeit verbessert.                                                |

|                | ADR 2: Datenbank-Umgebung                                                                                                                                                                                                                               |
|----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| *Kontext*      | Es ist wichtig, dass die Datenbank-Umgebung konsistent ist und leicht über verschiedenen Entwicklungsumgebungen hinweg verwendet werden kann.                                                                                                           |
| *Entscheidung* | Docker                                                                                                 |
| *Begründung*   | Docker bietet eine einfache Möglichkeit, eine konsistente Datenbank-Umgebung zu gewährleisten und macht die Einrichtung einfach und schnell.|
| *Konsequenzen* | Zum Starten und Verwalten des Datenbank-Containers müssen die entsprechenden Docker-Befehle bekannt sein und verwendet werden. Große Vorteile sind die erhöhte Konsistenz und Replizierbarkeit z.B. im Vergleich zur lokalen Ausführung von PostgreSQL. |

|                | ADR 3: Kommunikationsprotokoll                                                                                                                                                                                                                     |
|----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| *Kontext*      | Für die Kommunikation und den Datenaustausch zwischen Frontend und Backend, sowie Backend und externer API wird ein zuverlässiges und skalierbares Protokoll benötigt.                                                                             |
| *Entscheidung* | REST<br/>                                                                                                                                                                                                                                          |
| *Begründung*   | REST ist ein weit verbreiteter Standard für webbasierte Anwendungen. Es bietet die Vorteile, dass es zustandslos ist und zudem leicht verständlich, weil die Verwendung von HTTP-Methoden intuitiv ist und sich gut mit den CRUD-Operationen deckt.|
| *Konsequenzen* | Die Endpunkte müssen entsprechend der REST-Vorgaben strukturiert werden, da REST stark von der richtigen Gestaltung und Definition abhängig ist.                                                                                                   |


# 10. Qualität
**Qualitätsanforderungen**

| Qualitätsaspekt            | Anforderung                            | Erklärung                                                                                                                | Umsetzung/ Sicherstellung                                                                                                                   |
|----------------------------|----------------------------------------|--------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| **Wartbarkeit**            | Testbarkeit                            | Die Anwendung soll durch modularen Aufbau zuverlässig testbar sein und eine Testabdeckung von mindestens 80% aufweisen.  | Statische Codeanalyse in SonarCloud, Integration der Testcoverage                                                                           |
|                            | Aktualisierbarkeit und Erweiterbarkeit | Die Struktur der Anwendung sollte eine einfache und schnelle Aktualisierung und Erweiterung ermöglichen.                 | MVC-Pattern, Aktualisieren von Dependencies über Dependabot Pull-Requests, Dokumentation der Anwendung                                      |
|                            | Wirtschaftlichkeit                     | Die Kosten für Entwicklung und Betrieb der Anwendung sollen durch die Verwendung von Open-Source-Tools minimiert werden. | Wahl des Technologie-Stacks                                                                                                                 |
| **Security**               | Datensicherheit                        | Im Anwendungscode sollten keine sensiblen Daten wie Passwörter oder Keys zu finden sein.                                 | Konfiguration über Environment-Variablen und Github Secrets                                                                                 |
|                            | Sicherheitsüberprüfung und -aktualisierung | Die Sicherheit und Aktualität der Anwendung sollte stets gewährleistet sein.                                         | Automatische Testausführung über Pipeline, Überprüfung auf Sicherheitslücken durch Sonarcloud, Aktualität der Dependencies durch Dependabot |
| **Usability**              | Benutzerfreundlichkeit                 | Die Benutzeroberfläche soll intuitiv und einfach zu bedienen sein, auch für Erstbenutzer.                                | Benutzerfreundliches UI-Design, Verwendung von Angular Material, End-to-End Tests, manuelle Tests                                           |
| **Functional Suitability** | Funktionale Eignung                    | Die Anwendung sollte die grundlegenden Anforderungen erfüllen, wie das Bereitstellen von Quizfragen                      | Unit- und Integrationstests, manuelle Tests                                                                                                 |
| **Reliability**            | Zuverlässigkeit                        | Die Anwendung sollte stabil laufen und nicht unerwartet abstürzen                                                        | Last- und End-to-End Tests                                                                                                                  |


**Qualitätssichernde Maßnahmen**

| Maßnahme                | Tools                                       | Begründung Wahl des Tools                                                                                                                                               |
|-------------------------|---------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| *Unit Tests*            | Frontend: Jest<br/> Backend: JUnit, Mockito | Jest: Einfache Testerstellung und schneller als z.B. Karma<br/>JUnit<br/>Mockito: Einfache Mock-Erstellung und Verhaltenssimulation                                     |
| *Integration Tests*     | JUnit                                       | Bietet Vielzahl von Annotationen und Assertionen, die speziell für Integrationstests entwickelt wurden                                                                  |
| *E2E Tests*             | Cypress                                     | Einfacher Schreibstil und umfangreiche Funktionalitäten                                                                                                                 |
| *Last Tests*            | k6                                          | Einfache Verwendung und umfangreiche Funktionen                                                                                                                         |
| *Statische Codeanalyse* | Backend: SonarCloud<br/>Frontend: ESLint    | SonarCloud: Umfangreiche Funktionen zur Erkennung von Code-Qualitätsproblemen und Sicherheitslücken<br/>ESLint: flexibles Linting-Tool, speziell für JS-/TS-Anwendungen |
| *Securitytests*         | Dependabot                                  | Einfache Integration für automatische Pull-Requests bei Aktualisierungen und Sicherheitsprobleme                                                                        |

Von weiteren Performancetests wurde aufgrund des Limits der externen API abgesehen.
Der vorhandene Lasttest testet die Anwendung bis ans Limit von 180 Anfragen pro Minute.
Durch intensives Testen wurde ermittelt, dass die API zusätzlich zum Limit der Anfragen pro Minute auch ein Tageslimit von 3000 Anfragen enthält, auch wenn das in der Doku nicht erwähnt ist.

Durch die Integration der Maßnahmen in die Pipeline der Anwendung wird garantiert, dass diese bei jeder Änderung im Repository überprüft werden.
Für die Wahl der Tools wurden außerdem die aktive Community, die Aktualität, die Vorkenntnisse der Entwicklerin und die Unterstützung berücksichtigt.

# 11. Risiken und technische Schulden
- **Limit der externen API**: Die externe Quiz-API ist auf 180 Anfragen pro Minute begrenzt, wodurch die tragbare Last des Systems stark eingeschränkt ist.
Um eine bessere Usability zu erreichen, sollte ein Fallback eingebaut werden und dem Nutzer in der Oberfläche angezeigt werden, wenn dieses Limit erreicht ist. 
Zusätzlich besteht vermutlich ein Tageslimit der API, wie bereits im vorherigen Punkt erwähnt. 
- **Abhängigkeit von der externen API**: Die Funktionalität der Anwendung hängt von der Quiz-API ab. Es ist wichtig, den möglichen Ausfall der API zu bedenken.
Als Fallback werden in der Datenbank 10 Fragen gespeichert.
- **Code Smells**: Um die technischen Schulden der Anwendung gering zu halten, ist darauf zu achten die Code Smells, die von SonarCloud und ESLint erkannt werden stets zu beseitigen
- **Testabdeckung**: Es sollte darauf geachtet werden, die Testabdeckung sowohl für Frontend, als auch für Backend über 80 % zu halten, um potenzielle Risiken bestmöglich zu vermeiden.
- **Passwörter und Keys**: Beim Weiterentwickeln der Anwendung muss auch in Zukunft darauf geachtet werden, dass sensible Daten wie Passwörter nicht im Code zu finden sind. Außerdem sind sie nach wie vor über die Verfolgung der Git Commits einsehbar, das müsste im Bestfall bereinigt werden.


___

>**Der Aufbau dieser Dokumentation orientiert sich am offiziellen arc42-Template**
>
>**About arc42**
>
>arc42, the template for documentation of software and system
>architecture.
>
>Template Version 8.2 EN. (based upon AsciiDoc version), January 2023
>
>Created, maintained and © by Dr. Peter Hruschka, Dr. Gernot Starke and
>contributors. See <https://arc42.org>.
>
