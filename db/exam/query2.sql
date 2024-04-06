-- Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании.
-- Нужно учесть, что таких компаний может быть несколько.

SELECT 
  company.name, 
  Count(person.id) AS staff_count 
FROM 
  company 
  LEFT JOIN person ON company.id = person.company_id 
GROUP BY 
  company.id 
HAVING 
  Count(person.id) = (
    SELECT 
      Max(staff_count) 
    FROM 
      (
        SELECT 
          Count(person.id) AS staff_count 
        FROM 
          company 
          LEFT JOIN person ON person.company_id = company.id 
        GROUP BY 
          company.id
      )
  )