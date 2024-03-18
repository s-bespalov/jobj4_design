(SELECT name
	FROM movie
	EXCEPT 
	SELECT title
	FROM book)
UNION
(SELECT title
	FROM book
	EXCEPT
	SELECT name
	FROM movie)
