-- имена всех person, которые не состоят в компании с id = 5
-- название компании для каждого человека.
SELECT person.name, company.name
FROM person
LEFT JOIN company
ON person.company_id = company.id
WHERE person.company_id != 5