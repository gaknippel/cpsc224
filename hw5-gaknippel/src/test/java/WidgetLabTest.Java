package cpsc224;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;



public class WidgetLabTests {


@Test
    public void testPanelValues() {
        
        WidgetLab lab = new WidgetLab();

        assertEquals("", lab.textField1.getText());
        
        assertEquals("Click button", lab.label2.getText());

        assertEquals("Waiting for Key 1 & 2", lab.label6.getText());
        assertFalse(lab.toggle1.isSelected());
        assertFalse(lab.toggle2.isSelected());

        assertEquals("first item", lab.comboBox4.getSelectedItem());
        
        assertEquals(50, lab.slider7.getValue());
        assertEquals(50, (Integer)lab.spinner7.getValue());
        assertEquals(50, lab.progressBar7.getValue());
    }
}