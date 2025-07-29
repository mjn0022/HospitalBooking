# HospitalBooking


## 1.eerd diagram

```mermaid
erDiagram

    PatientInfo {
        INT patient_id PK
        STRING name
        DATE dob
        STRING gender
        STRING phone_no
        STRING email
    }

    PatientAddress {
        INT address_id PK
        INT patient_id FK
        STRING address_line
        STRING city
        STRING state
        STRING zipcode
        FLOAT latitude
        FLOAT longitude
    }

    PatientAvailability {
        INT availability_id PK
        INT patient_id FK
        INT address_id FK
        DATE availability_date
        STRING slot
    }

    HospitalInfo {
        INT hospital_id PK
        STRING name
        STRING phone_no
        STRING address
        FLOAT latitude
        FLOAT longitude
    }

    HospitalAvailability {
        INT availability_id PK
        INT hospital_id FK
        DATE availability_date
        STRING slot
        BOOLEAN slot_available
    }

    Booking {
        INT booking_id PK
        INT patient_id FK
        INT address_id FK
        INT hospital_id FK
        DATE appointment_date
        STRING slot
    }

    PatientInfo ||--o{ PatientAddress : has
    PatientInfo ||--o{ PatientAvailability : has
    PatientAddress ||--o{ PatientAvailability : has
    HospitalInfo ||--o{ HospitalAvailability : has
    PatientInfo ||--o{ Booking : books
    PatientAddress ||--o{ Booking : from
    HospitalInfo ||--o{ Booking : at
```


## 🏥 C2 Architecture - Container View

```mermaid
flowchart TD
    user[User / Postman]
    patientSvc[Container: Patient Service<br>Handles patient info, address, availability]
    hospitalSvc[Container: Hospital Service<br>Manages hospital info and availability]
    bookingSvc[Container: Booking Service<br>Coordinates bookings & applies logic]
    geocodingSvc[Container: Geocoding Service<br>Calls Nominatim API]
    haversineUtil[Container: Haversine Utility<br>Computes distance between coordinates]

    patientDB[(Database: Patient DB)]
    hospitalDB[(Database: Hospital DB)]
    bookingDB[(Database: Booking DB)]

    nominatim[Nominatim API<br>Public Geocoding Service]

    user --> patientSvc
    user --> hospitalSvc
    user --> bookingSvc

    patientSvc --> geocodingSvc
    geocodingSvc --> nominatim

    bookingSvc --> patientSvc
    bookingSvc --> hospitalSvc
    bookingSvc --> haversineUtil

    patientSvc --> patientDB
    hospitalSvc --> hospitalDB
    bookingSvc --> bookingDB

```

## 🏥 C4 Architecture – Container View (Simplified)

```mermaid
graph TD
  user[User]
  subgraph "Hospital Appointment Booking System"
    patientSvc[Patient Service<br/>Manages patient info, address, availability]
    hospitalSvc[Hospital Service<br/>Handles hospital info and slot availability]
    bookingSvc[Booking Service<br/>Orchestrates appointments based on logic]
    geocodeSvc[Geocoding Service<br/>Uses Nominatim API to convert address to coordinates]
    haversine[Haversine Utility<br/>Calculates distance between coordinates]

    patientDB[(Patient DB)]
    hospitalDB[(Hospital DB)]
    bookingDB[(Booking DB)]
  end

  nominatim[Nominatim API<br/>External Geocoding Service]

  user --> bookingSvc
  user --> patientSvc
  user --> hospitalSvc

  patientSvc --> geocodeSvc
  geocodeSvc --> nominatim

  bookingSvc --> patientSvc
  bookingSvc --> hospitalSvc
  bookingSvc --> haversine

  patientSvc --> patientDB
  hospitalSvc --> hospitalDB
  bookingSvc --> bookingDB


