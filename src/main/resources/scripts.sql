CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);




CREATE TABLE IF NOT EXISTS public.space_fleet_memberships
(
    membership_id SERIAL PRIMARY KEY,
    membership_tier VARCHAR(50) NOT NULL,
    membership_credits INTEGER NOT NULL,
    membership_log TEXT,
    date_registered DATE DEFAULT CURRENT_DATE,
    astronaut_id INTEGER NOT NULL,
    CONSTRAINT space_fleet_memberships_astronaut_fkey FOREIGN KEY (astronaut_id)
    REFERENCES public.astronauts (astronaut_id)
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    );

TABLESPACE pg_default;


-- Getting sum for sumberships by month example
SELECT
    TO_CHAR(date_purchased, 'YYYY-MM') AS month,
    SUM(membership_price) AS total_revenue
FROM public.memberships
GROUP BY month
ORDER BY month;
