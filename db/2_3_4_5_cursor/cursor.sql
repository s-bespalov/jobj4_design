BEGIN;
DECLARE products_backward SCROLL CURSOR FOR
SELECT * FROM products;

MOVE FORWARD 19 FROM products_backward;
FETCH NEXT FROM products_backward;

MOVE BACKWARD 4 FROM products_backward;
FETCH PRIOR FROM products_backward;

MOVE BACKWARD 7 FROM products_backward;
FETCH PRIOR FROM products_backward;

MOVE BACKWARD 4 FROM products_backward;
FETCH PRIOR FROM products_backward;

FETCH PRIOR FROM products_backward;

CLOSE products_backward;
COMMIT;