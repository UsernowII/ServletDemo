package org.aguzman.apiservlet.webapp.session.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {

    List< ItemCarro> items;


    public Carro() {
        this.items = new ArrayList<>();
    }

    public List<ItemCarro> getItems() {
        return items;
    }

    public void addItemCarro(ItemCarro itemCarro){
        if (items.contains(itemCarro)){
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(i -> i.equals(itemCarro))
                    .findAny();
            if (optionalItemCarro.isPresent()){
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad()+1);
            }

        }else{
            this.items.add(itemCarro);
        }

    }

    public int getTotal(){
        return items.stream().mapToInt(ItemCarro::getCantidadXProducto).sum();
    }

    public void removeProductos(List<String> productoIds) {
        if (productoIds != null){
            productoIds.forEach( pId-> removeProducto(pId));
        }
    }

    private void removeProducto(String pId) {
        Optional<ItemCarro> producto = findProducto(pId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    public void updateCantidad(String productoId, int cantidad) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    private Optional<ItemCarro> findProducto(String productoId) {
        return items.stream()
                .filter( itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }
}
