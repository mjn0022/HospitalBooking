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
