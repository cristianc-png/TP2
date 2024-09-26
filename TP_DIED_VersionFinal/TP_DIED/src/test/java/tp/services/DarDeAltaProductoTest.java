package tp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tp.dao.ProductoRepository;
import tp.modelo.Producto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DarDeAltaProductoTest {

	@InjectMocks
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDarDeAlta_NombreExcedeLimite() {
        Producto producto = new Producto();
        producto.setNombre("NombreDemasiadoLargoParaPrueba");

        assertThrows(IllegalArgumentException.class, () -> {
            productoService.darDeAlta(producto);
        });

        verify(productoRepo, never()).darDeAlta(any()); // Verifica que el método no se llama
    }

    @Test
    public void testDarDeAlta_NombreDuplicado() {
        Producto producto = new Producto();
        producto.setNombre("NombreValido");

        when(productoRepo.existeNombre("NombreValido")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            productoService.darDeAlta(producto);
        });

        verify(productoRepo, never()).darDeAlta(any()); // Verifica que el método no se llama
    }

    @Test
    public void testDarDeAlta_NombreValido() {
        Producto producto = new Producto();
        producto.setNombre("NombreValido");
        producto.setDescripcion("Descripción válida");

        when(productoRepo.existeNombre("NombreValido")).thenReturn(false);
        when(productoRepo.darDeAlta(producto)).thenReturn(producto); // Simula la respuesta del repositorio

        Producto resultado = productoService.darDeAlta(producto);

        verify(productoRepo, times(1)).darDeAlta(producto); // Verifica que el método se llama una vez
    }

    @Test
    public void testDarDeAlta_DescripcionExcedeLimite() {
        Producto producto = new Producto();
        producto.setNombre("NombreValido");
        producto.setDescripcion("DescripciónMuyLargaParaPruebaQueExcedeElLimiteDeCaracteres");

        when(productoRepo.existeNombre("NombreValido")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            productoService.darDeAlta(producto);
        });

        verify(productoRepo, never()).darDeAlta(any()); // Verifica que el método no se llama
    }
}