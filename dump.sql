-- H2 2.2.224; 
;              
CREATE USER IF NOT EXISTS "SA" SALT '8a8c3297f3ed09e4' HASH '2d94b9b89a74059f3f2ea9b24de0e9285b8c1a468a6d26c1c835568a087490aa' ADMIN;          
CREATE MEMORY TABLE "PUBLIC"."ROLES"(
    "ID" UUID NOT NULL,
    "CREATED_AT" TIMESTAMP(6) WITH TIME ZONE,
    "DESCRIPTION" CHARACTER VARYING(150) NOT NULL,
    "NAME" CHARACTER VARYING(40) NOT NULL
);    
ALTER TABLE "PUBLIC"."ROLES" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ID");         
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.ROLES;    
CREATE MEMORY TABLE "PUBLIC"."USERS"(
    "ID" UUID NOT NULL,
    "CREATED_AT" TIMESTAMP(6) WITH TIME ZONE,
    "EMAIL" CHARACTER VARYING(128) NOT NULL,
    "FIRST_NAME" CHARACTER VARYING(45) NOT NULL,
    "LAST_NAME" CHARACTER VARYING(45) NOT NULL,
    "PASSWORD" CHARACTER VARYING(64) NOT NULL,
    "PHOTO" CHARACTER VARYING(128),
    "STATUS" CHARACTER VARYING(30) NOT NULL
);    
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4D4" PRIMARY KEY("ID");       
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.USERS;    
CREATE MEMORY TABLE "PUBLIC"."USERS_ROLES"(
    "USER_ID" UUID NOT NULL,
    "ROLE_ID" UUID NOT NULL
);        
ALTER TABLE "PUBLIC"."USERS_ROLES" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_A" PRIMARY KEY("USER_ID", "ROLE_ID");   
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.USERS_ROLES;              
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4D" CHECK("STATUS" IN('INACTIVE', 'ACTIVE')) NOCHECK;         
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."UK_6DOTKOTT2KJSP8VW4D0M25FB7" UNIQUE("EMAIL");           
ALTER TABLE "PUBLIC"."ROLES" ADD CONSTRAINT "PUBLIC"."UK_OFX66KERUAPI6VYQPV6F2OR37" UNIQUE("NAME");            
ALTER TABLE "PUBLIC"."USERS_ROLES" ADD CONSTRAINT "PUBLIC"."FKJ6M8FWV7OQV74FCEHIR1A9FFY" FOREIGN KEY("ROLE_ID") REFERENCES "PUBLIC"."ROLES"("ID") NOCHECK;     
ALTER TABLE "PUBLIC"."USERS_ROLES" ADD CONSTRAINT "PUBLIC"."FK2O0JVGH89LEMVVO17CBQVDXAA" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;     
