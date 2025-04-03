CREATE TABLE IF NOT EXISTS users (
                    userId SERIAL PRIMARY KEY,
                    userName VARCHAR(50) NOT NULL,
                    userPassword VARCHAR(100) NOT NULL,
                    userEmail VARCHAR(100) NOT NULL UNIQUE,
                    userPhoneNum VARCHAR(20) NOT NULL,
                    userAddress VARCHAR(255) NOT NULL,
                    userEmergencyContactName VARCHAR(50),
                    userEmergencyContactPhoneNum VARCHAR(20),
                    userRole VARCHAR(20) NOT NULL,
                    userCreationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS memberships (
                    membershipId SERIAL PRIMARY KEY,
                    membershipType VARCHAR(50) NOT NULL,
                    membershipDescription TEXT,
                    membershipCost DECIMAL(10,2) NOT NULL,
                    userId INTEGER NOT NULL,
                    CONSTRAINT membershipFkey FOREIGN KEY (userId)
                        REFERENCES public.users(userId)
                        ON UPDATE NO ACTION
                        ON DELETE CASCADE,
                    dateRegistered DATE DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS gymClasses (
                    gymClassId SERIAL PRIMARY KEY,
                    gymClassType VARCHAR(50) NOT NULL,
                    gymClassDescription TEXT,
                    trainerID INTEGER,
                    CONSTRAINT trainerFkey FOREIGN KEY (trainerId)
                        REFERENCES public.users(userId)
                        ON UPDATE CASCADE
                        ON DELETE SET NULL,
                    classCreated DATE DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS classEnrollments (
                    enrollmentId SERIAL PRIMARY KEY,
                    studentId INTEGER NOT NULL,
                    CONSTRAINT studentFkey FOREIGN KEY (studentId)
                        REFERENCES public.users(userID)
                        ON UPDATE NO ACTION
                        ON DELETE CASCADE,
                    gymClassId INTEGER NOT NULL,
                    CONSTRAINT gymClassFkey FOREIGN KEY (gymClassId)
                        REFERENCES public.gymClasses(gymClassId)
                        ON UPDATE NO ACTION
                        ON DELETE CASCADE,
                    enrollmentDate DATE DEFAULT CURRENT_DATE,
                    CONSTRAINT uniqueEnrollment UNIQUE (studentId, gymClassId)
);
