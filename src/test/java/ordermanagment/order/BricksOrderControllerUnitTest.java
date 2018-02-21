package ordermanagment.order;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordermanagement.order.model.BricksOrder;
import com.ordermanagement.order.services.BricksOrderService;
import com.ordermanagment.order.controller.BricksOrderController;



	public class BricksOrderControllerUnitTest {

	    private static final long UNKNOWN_ID = Integer.MAX_VALUE;

	    private MockMvc mockMvc;

	    @Mock
	    private BricksOrderService bricksOrderService;

	    @InjectMocks
	    private BricksOrderController bricksOrderController;

	    @Before
	    public void init(){
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders
	                .standaloneSetup(bricksOrderController)
	                .build();
	    }

	    // =========================================== Get All Users ==========================================

	    @Test
	    public void testGetAllBricksOrdersSuccess() throws Exception {
	        List<BricksOrder> orders = Arrays.asList(
	                new BricksOrder(1L, 10L),
	                new BricksOrder(2L, 30L));

	        when(bricksOrderService.getBricksOrderList()).thenReturn(orders);

	        mockMvc.perform(get("/bricksOrders"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

	        verify(bricksOrderService, times(1)).getBricksOrderList();
	        verifyNoMoreInteractions(bricksOrderService);
	    }

	    // =========================================== Get User By ID =========================================

	    @Test
	    public void test_get_by_orderReference_success() throws Exception {
	    	BricksOrder bricksOrder = new BricksOrder(1L, 10L);

	        when(bricksOrderService.getBricksOrder(1L)).thenReturn(bricksOrder);

	        mockMvc.perform(get("/bricksOrder/{id}", 1))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

	        verify(bricksOrderService, times(1)).getBricksOrder(1L);
	        verifyNoMoreInteractions(bricksOrderService);
	    }

	    @Test
	    public void test_get_by_orderReference_fail_404_not_found() throws Exception {

	        when(bricksOrderService.getBricksOrder(1L)).thenReturn(null);

	        mockMvc.perform(get("/bricksOrder/{id}", 1L))
	                .andExpect(status().isNotFound());

	        verify(bricksOrderService, times(1)).getBricksOrder(1L);
	        verifyNoMoreInteractions(bricksOrderService);
	    }

	    // =========================================== Create New User ========================================

	    @Test
	    public void test_create_order_success() throws Exception {
	    	BricksOrder bricksOrder = new BricksOrder(20L);

	        when(bricksOrderService.saveBricksOrder(bricksOrder)).thenReturn(1L);

	        mockMvc.perform(
	                post("/bricksOrder")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(bricksOrder)));

	        verify(bricksOrderService, times(1)).saveBricksOrder(bricksOrder);
	        verifyNoMoreInteractions(bricksOrderService);
	    }

	    // =========================================== Update Existing User ===================================

	    @Test
	    public void test_update_order_success() throws Exception {
	    	BricksOrder bricksOrder = new BricksOrder(1L, 50L);

	        when(bricksOrderService.getBricksOrder(1L)).thenReturn(bricksOrder);
	        when(bricksOrderService.updateBricksOrder(bricksOrder.getOrderReference(),bricksOrder)).thenReturn(1L);

	        mockMvc.perform(
	                put("/bricksOrder/{id}", bricksOrder.getOrderReference())
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(bricksOrder)));
	               // .andExpect(status().isOk());

	        //verify(bricksOrderService, times(1)).getBricksOrder(1L);
	        verify(bricksOrderService, times(1)).updateBricksOrder(bricksOrder.getOrderReference(),bricksOrder);
	        verifyNoMoreInteractions(bricksOrderService);
	    }

	    @Test
	    public void test_update_user_fail_404_not_found() throws Exception {
	    	BricksOrder bricksOrder = new BricksOrder(UNKNOWN_ID, 60L);

	        //when(bricksOrderService.getBricksOrder(1L)).thenReturn(null);
	        when(bricksOrderService.updateBricksOrder(1L,bricksOrder)).thenReturn(0L);

	        mockMvc.perform(
	                put("/bricksOrder/{id}", 1L)
	                        .contentType(MediaType.APPLICATION_JSON));
	                

	        verify(bricksOrderService, times(1)).updateBricksOrder(1L,bricksOrder);
	        verifyNoMoreInteractions(bricksOrderService);
	    }
	    
	    public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }


}
