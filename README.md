# Museums Quiz
[Base de datos para ver la estructura](https://github.com/makelele29/MuseumQR/blob/master/bd.json)
## Descripción
El juego consiste en ir ganando monedas acertando una serie de preguntas al leer un
código QR .

En un mismo código QR puede tener infinitas preguntas,todas tendrán una preguntas y las
distintas tipos respuestas son:
- Spinner: saldrá un ruleta con números y podrás seleccionar la respuesta correcta.
- Número: saldrá el teclado pero solo podrás añadir números, ya que la respuesta es
numérica.
- Texto: saldrá el teclado y podrás añadir la respuesta que quieras.
- 4 respuestas: saldrá 4 botones con las posibles respuestas correcta pero solo una
será la verdadera.

Cada pregunta puede tener una cantidad distinta de monedas.
Con las monedas que obtengas de las preguntas que has solucionado puedes canjearlas
por premios leyendo un código QR.

Todas las preguntas pueden estar en varios idiomas dependiendo del lenguaje que tenga
android, en el caso de querer añadir un idioma nuevo no haría falta actualizar la aplicación,
ya que la base de datos y la app están bien configuradas para ello.

Todas las preguntas tienen 3 intentos en los cual por cada intento fallido ganas menos
monedas, en el caso de acertar esa pregunta ya no te aparecerá más y si pierdes la
pregunta se repetirá.

Se pueden conseguir una serie de medallas por darle más jugabilidad a la app y te notificara
la app cuando las vayas consiguiendo, además, tendrás una sección medallas en las que
podrás ver las que te faltan y las bloqueadas.

## Código para probar la Aplicación

El mismo código sirve para probar toda la aplicación y si vamos cambiando de dificultades
aparecerán distintas preguntas.
Ahora mismo las preguntas están en inglés y español.

Los distintos niveles de dificultad son:
- __Fácil__: 4 preguntas
- __Medio__: 2 preguntas
- __Difícil__: 1 pregunta

![qr](http://chart.googleapis.com/chart?cht=qr&chs=500x500&chl={%22qr%22=%22qr1%22})

Si se respondes todas se conseguirán todas las medallas, esto es una prueba para ver el
dinamismo de la aplicación, se podrían tener infinitos QRs con infinitas preguntas de 4 tipos
distintos y las medallas se podrían conseguir con un solo QR o con varios.

## Canjear monedas

Las monedas conseguidas se podrán canjear por premios, por ejemplo en este QR se
conseguirá un lápiz por 135 monedas, al igual que las preguntas, los premios se pueden
modificar o añadir más.

![](http://chart.googleapis.com/chart?cht=qr&chs=500x500&chl={%22canjear%22=1})

