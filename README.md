# DemoAppCarroClienteV1
Demo App Carro Cliente V1

La idea de esta app, es que el usuario pueda seleccionar los productos que el proveedor tiene disponible en su stock y enviar un mensaje por WhatsApp
o/y a un ApiService o algo similar.

Trato de crear mis proyectos con :
a) Patron MVVM

b) Arquitectura Clean

c) Algo de Solid

d) ROOM Database

e) Retrofit

f) Inyeccion de dependencias con dagger/hilt

Las imagenes de los productos presentados son de referencia, no poseo los copyright de ellas.

Agradezco los contenidos entregados por los cientos de entuciasta del desarrollo de software que nos compaerten sus conocimientos.

El proyecto tiene algunos bugs detectados, como por ejemplo.
           - Al actualizar el productos agregado al carro de compras, el total a pagar en el pedido no se actualiza en el textview, pero en la base de datos el valor
           es el correcto, creo que es un error con el dao o el CarroCompraViewModel, que por algun motivo que desconozco, no informa al
           totalPedidoCarroCompra = MutableLiveData, que su valor a cambiado y en respuesta el observer del MainActivity del totalPedidoCarroCompra no se actualiza.
           He probado metodos, pero todos dan el mismo resultado.
           Me gustaria probar con Flow, a ver si esto sigue ocurriendo lo mismo o se corrije.
           

....
Mas info a medida que continua escalando el proyecto...

