package projet.jsf.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return formatter.parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date format is incorrect. Expected format: " + DATE_FORMAT, e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Date) {
            return formatter.format((Date) value);
        } else {
            throw new IllegalArgumentException("Expected a Date object.");
        }
    }
}

