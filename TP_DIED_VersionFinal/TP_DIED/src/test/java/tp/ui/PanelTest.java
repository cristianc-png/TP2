package tp.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tp.dao.ProductoRepository;
import tp.modelo.Producto;
import tp.services.ProductoService;

class PanelTest {

	  @InjectMocks
	  private ProductoService productoService;
	  
	  @Mock
	  private ProductoRepository mockRepository;

	  @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }


	    @Test
	    public void testBuscarPorPrecio_ProductosEncontrados() {
	        // Configurar el comportamiento del mock para simular productos encontrados
	        double precioBuscado = 50.0;
	        List<Producto> productosEncontrados = Arrays.asList(
	            new Producto("Producto1", "Descripción 1", 50.0, 0.5),
	            new Producto("Producto2", "Descripción 2", 50.0, 0.6)
	        );
	        when(mockRepository.buscarPorPrecio(precioBuscado)).thenReturn(productosEncontrados);

	        // Llamar al método bajo prueba
	        List<Producto> resultado = productoService.buscarPorPrecio(precioBuscado);

	        // Verificar que se llamó al método del mock y que se devolvió el resultado esperado
	        verify(mockRepository).buscarPorPrecio(precioBuscado);
	        assertEquals(productosEncontrados, resultado);
	    }

	    @Test
	    public void testBuscarPorPrecio_ProductosNoEncontrados() {
	        // Configurar el comportamiento del mock para simular productos no encontrados
	        double precioBuscado = 100.0;
	        List<Producto> productosVacios = Collections.emptyList();
	        when(mockRepository.buscarPorPrecio(precioBuscado)).thenReturn(productosVacios);

	        // Llamar al método bajo prueba
	        List<Producto> resultado = productoService.buscarPorPrecio(precioBuscado);

	        // Verificar que se llamó al método del mock y que no se encontraron productos
	        verify(mockRepository).buscarPorPrecio(precioBuscado);
	        
	        //verificar la lista vacía
	        assertTrue(resultado.isEmpty());
	    }
	
	    @Test
	    public void testServiceBuscarPorPeso_ProductosEncontrados() {
	        double pesoBuscado = 0.5;
	        List<Producto> productosEncontrados = Arrays.asList(
	            new Producto("Producto1", "Descripción 1", 50.0, 0.5),
	            new Producto("Producto2", "Descripción 2", 50.0, 0.6)
	        );
	        when(mockRepository.buscarPorPeso(pesoBuscado)).thenReturn(productosEncontrados);

	        // Llamar al método bajo prueba
	        List<Producto> resultado = productoService.buscarPorPeso(pesoBuscado);

	        // Verificar que se llamó al método del mock y que se devolvió el resultado esperado
	        verify(mockRepository).buscarPorPeso(pesoBuscado);
	        assertEquals(productosEncontrados, resultado);
	    }

	    @Test
	    public void testServiceBuscarPorPeso_ProductosNoEncontrados() {
	        double pesoBuscado = 1.0;
	        List<Producto> productosVacios = Collections.emptyList();
	        when(mockRepository.buscarPorPeso(pesoBuscado)).thenReturn(productosVacios);
	        

	        // Llamar al método bajo prueba
	        List<Producto> resultado = productoService.buscarPorPeso(pesoBuscado);

	        // Verificar que se llamó al método del mock y que no se encontraron productos
	        verify(mockRepository).buscarPorPeso(pesoBuscado);
	        
	        //verificar la lista vacía
	        assertTrue(resultado.isEmpty());
	    }
	    
}
