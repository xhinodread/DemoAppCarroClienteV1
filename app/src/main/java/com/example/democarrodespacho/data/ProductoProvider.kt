package com.example.democarrodespacho.data

import com.example.democarrodespacho.domain.model.Producto

class ProductoProvider {
    companion object{
        var productoList = mutableListOf<Producto>(
            Producto(1, "Carne Molida", 3400, "https://eldiariony.com/wp-content/uploads/sites/2/2022/04/carne-molida-shutterstock_316382753.jpg?quality=80&strip=all&w=560"),
            Producto(1, "Toalla Nova", 3490, "https://images.lider.cl/wmtcl?source=url[file:/productos/731619a.jpg]&sink"),
            Producto(1, "Leche Entera Natural (Caja)", 969, "http://images.lider.cl/wmtcl?source=url[file:/productos/702661a.jpg]&sink"),
            Producto(1, "Jamón Pierna corte pluma", 1790, "http://images.lider.cl/wmtcl?source=url[file:/productos/1098221a.jpg]&sink"),
            Producto(1, "Queso mantecoso", 4590, "https://images.lider.cl/wmtcl?set=imageSize[medium],imageURL[file:/productos/806638a.jpg],options[progressive]&call=url[file:catalog/sizing.chain]&sink=format[jpg],options[progressive]"),
            Producto(1, "Cerveza Lager, 710 ml", 1000, "https://images.lider.cl/wmtcl?source=url[file:/productos/11413a.jpg]&scale=size[300x300]&sink"),
        )
    }
}

/****
 *
var productoList = mutableListOf<Producto>(
Producto(1, "Carne Molida", 3400, "https://eldiariony.com/wp-content/uploads/sites/2/2022/04/carne-molida-shutterstock_316382753.jpg?quality=80&strip=all&w=560"),
Producto(1, "Toalla Nova", 3490, "https://images.lider.cl/wmtcl?source=url[file:/productos/731619a.jpg]&sink"),
Producto(1, "Leche Entera Natural (Caja)", 969, "http://images.lider.cl/wmtcl?source=url[file:/productos/702661a.jpg]&sink"),
Producto(1, "Jamón Pierna corte pluma", 1790, "http://images.lider.cl/wmtcl?source=url[file:/productos/1098221a.jpg]&sink"),
Producto(1, "Queso mantecoso", 4590, "https://images.lider.cl/wmtcl?set=imageSize[medium],imageURL[file:/productos/806638a.jpg],options[progressive]&call=url[file:catalog/sizing.chain]&sink=format[jpg],options[progressive]"),
Producto(1, "Cerveza Lager, 710 ml", 1000, "https://images.lider.cl/wmtcl?source=url[file:/productos/11413a.jpg]&scale=size[300x300]&sink"),
)
 *
 *
val productoList = listOf<Producto>(
    Producto(1, "Carne Molida", 3400, "http://192.168.1.82/img/carne-molida.webp"),
    Producto(1, "Toalla Nova", 3490, "http://192.168.1.82/img/tnova.jfif"),
    Producto(1, "Leche Entera Natural (Caja)", 969, "http://192.168.1.82/img/leche.jfif"),
    Producto(1, "Jamón Pierna corte pluma", 1790, "http://192.168.1.82/img/jamon.jfif"),
    Producto(1, "Queso mantecoso", 4590, "http://192.168.1.82/img/queso.jfif"),
)
 *
 */