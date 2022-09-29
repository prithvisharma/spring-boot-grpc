CREATE TABLE
    "user"(
        id uuid DEFAULT uuid_generate_v4(),
        name varchar,
        age numeric,
        gender varchar,
        phone numeric,
        email varchar,
        address varchar,
        city varchar,
        state varchar,
        pincode numeric,
        PRIMARY KEY(id)
    );

INSERT INTO
    public."user"(
        name,
        age,
        gender,
        phone,
        email,
        address,
        city,
        state,
        pincode
    )
VALUES (
        'Prithvi Sharma',
        23,
        'MALE',
        9096410286,
        'prithvi2255@gmail.com',
        'Nalanda, Naigaon East',
        'Mumbai',
        'Maharashtra',
        401208
    );

INSERT INTO
    public."user"(
        name,
        age,
        gender,
        phone,
        email,
        address,
        city,
        state,
        pincode
    )
VALUES (
        'John Doe',
        33,
        'MALE',
        7506049527,
        'johndoe@gmail.com',
        'Gokuldham, Goregaon East',
        'Mumbai',
        'Maharashtra',
        400063
    );

INSERT INTO
    public."user"(
        name,
        age,
        gender,
        phone,
        email,
        address,
        city,
        state,
        pincode
    )
VALUES (
        'Prithvi Rampage',
        12,
        'MALE',
        9876543210,
        'prithvi@gmail.com',
        'Nowhere, Somewhere',
        'Mumbai',
        'Maharashtra',
        400000
    );