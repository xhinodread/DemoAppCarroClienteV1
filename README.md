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

Las imagenes de los productos presentados son de referencia, no poseo los copyright de ellas. Solo la propiedad intelectual sobre el codigo escrito en este proyecto.

Agradezco los contenidos entregados por los cientos de entuciasta del desarrollo de software que nos comparten sus conocimientos.

El proyecto tiene algunos bugs detectados, como por ejemplo.
           - Al actualizar el productos agregado al carro de compras, el total a pagar en el pedido no se actualiza en el textview, pero en la base de datos el valor
           es el correcto, creo que es un error con el dao o el CarroCompraViewModel, que por algun motivo que desconozco, no informa al
           totalPedidoCarroCompra = MutableLiveData, que su valor a cambiado y en respuesta el observer del MainActivity del totalPedidoCarroCompra no se actualiza.
           He probado metodos, pero todos dan el mismo resultado.
           Me gustaria probar con Flow, a ver si esto sigue ocurriendo lo mismo o se corrije.
           
Aun falta implementar los test unitarios
....
Mas info a medida que continua escalando el proyecto...

<p align="center"style="margin-left: 2.5em;padding: 0 7em 2em 0;border-width: 2px; border-color: red; border-style:solid;" >
  <img src="https://user-images.githubusercontent.com/12845540/204099740-1dd1bdde-f323-42cd-b0bd-93491ccfb383.PNG" title="Home app" style="padding: 10px;"  >
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://user-images.githubusercontent.com/12845540/204099739-2373ad9f-4f5d-421a-bfe5-06a12b1d33be.PNG" title="Error en la conección"  >
</p>

<p align="center" style="margin-left: 2.5em;padding: 0 7em 2em 0;border-width: 2px; border-color: white; border-style:solid;" >
  <img src="https://user-images.githubusercontent.com/12845540/204099744-aa96527b-7b81-4e49-9797-d481c2aa8aeb.PNG" title="Detalle del producto" >
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://user-images.githubusercontent.com/12845540/204099737-07402059-6836-4f69-a88f-70e404b3fd7c.PNG" title="Carrito de compras" >
</p>
