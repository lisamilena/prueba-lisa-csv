# prueba-lisa-csv
Prueba lista CSV

## CURL
Ejemplo de llamada (CURL)
> curl --location --request GET 'localhost:8080/products'

## Preguntas
### Estructuras de datos utilizadas en el algoritmo

He usado como estructura de datos **List** por su flexibilidad. Es una interfaz de Collections, que permite identificar los objetos independientemente de la implementación, con lo que da mayor flexibilidad a la ahora de trabajar. Además de poder decidir, en un momento determinado, con que implementación (ArrayList, LinkedList...) se quiere el resultado final.

Ya que los datos vienen de un recurso externo, pero cada uno identificado con un id numérico, no he creído que el uso de Set fuese necesario, ya que no habría que descartar duplicidades y de esta forma se perdería el orden de inserción, que en algunas ocasiones puede servir para manejar los datos, aunque sea en los test.

Por otra parte no he creído necesaria una estructura de datos tipo Map, ya que el algoritmo sobre todo trabajaba sobre los datos de size y stock.

### Complejidad temporal del algoritmo

El algoritmo que he implementado estaría en O(n2), que son los algoritmos cuyo tiempo de ejecución crece de forma proporcional al cuadrado del tamaño de su entrada, es decir que tienen bucles anidados.

En el caso particular de este algoritmo se anidaría la iteración del *filter* con los *anyMatch* o *noneMatch*.

Con lo que el calculo de complejidad temporal quedaría:

> f(x) = 2 + 1n * 1n + 1n * (1 + 2n) + 1
>
> f(x) = 3n^2 + 1n + 3

Visto que el resultado de la complejidad temporal aumenta tanto al anidar dos bucles, sería una buena mejora poder tener todos los datos en un mismo nivel, para tener un único nivel de bucles.