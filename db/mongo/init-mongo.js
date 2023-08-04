db.createUser({
    user: 'root',
    pwd: 'sasa',
    roles: [
        {
            role: 'readWrite',
            db: 'testDB',
        },
    ],
});
db.createCollection('app_users', { capped: false });

db.app_users.insert([

    {
        "username": "box1",
        "accountId": "1",
        "enabled": true,
        "password_not_encrypted": "box1234",
        "password": "$2a$10$9Lo7xGYdA8C8lSpSRm4zXepqZsFFZqqti.Y6Mu0WeC79ydftyBrA6",
        "role":
            {
                "granted_authorities": ["read", "write"]
            }
    },
    {
        "username": "boxadmin",
        "accountId": "1",
        "enabled": true,
        "password_not_encrypted": "box1234",
        "password": "$2a$10$9Lo7xGYdA8C8lSpSRm4zXepqZsFFZqqti.Y6Mu0WeC79ydftyBrA6",
        "role":
            {
                "granted_authorities": ["read", "write","ADMIN"]
            }
    },
    {
        "username": "box2",
        "accountId": "2",
        "enabled": true,
        "password_not_encrypted": "boxtest1234",
        "password": "$2a$10$9Lo7xGYdA8C8lSpSRm4zXepqZsFFZqqti.Y6Mu0WeC79ydftyBrA6",
        "role":
            {
                "granted_authorities": ["read", "write","ADMIN"]
            }
    }
]);
