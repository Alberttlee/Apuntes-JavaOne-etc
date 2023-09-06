public class TestDescuento {

    public static void main(String[] args) {
        /*
        María es propietaria de una tienda de ropa y le gustaría crear un programa que calcule el valor final
        del producto después de aplicar un descuento que ofrecería a sus clientes.

        Si el valor de la compra está entre $100.0 y $199.99, el descuento es del 10%.
        Si el valor de la compra está entre $200.0 y $299.99, el descuento es del 15%.
        Para compras superiores a $300.0, el descuento es del 20%.
        */

        double valorFinal, totalDescuento;
        double valorCompra = 250.0;
        int descuento;

        if (valorCompra >= 100.0 && valorCompra <= 199.99 ){
            descuento = 10;
        } else if (valorCompra >= 200.0 && valorCompra <= 299.99) {
            descuento = 15;
        } else if (valorCompra >= 300.0) {
            descuento = 20;
        } else {
            descuento = 0;
        }
        totalDescuento = valorCompra * (descuento / 100.0);
        valorFinal = valorCompra - totalDescuento;
        System.out.println("    La compra fue de: $" + valorCompra + "\n" +
                "  El descuento es de:  " + descuento + "%\n" +
                "   Total a descontar: $" + totalDescuento + "\n" +
                "       Total a pagar: $" + valorFinal);

    }
}
