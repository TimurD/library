package com.timur.library.tags;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
/**
 * Created by timur on 04.06.2017.
 */
public class DateReminderTag extends TagSupport {

    public static final Logger LOGGER = Logger.getLogger(DateReminderTag.class);

    private Date returnDate;

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


    public int doStartTag() {

        if(returnDate==null){
            return SKIP_BODY;
        }
        String content="";

        long days = (TimeUnit.MILLISECONDS.toDays(returnDate.getTime() - new Date().getTime()));

        if (days >=0) {
            content = days+1L + " left";
        } else {
            content = -days + " overdue";
        }

        try {
            pageContext.getOut().write(content);
        } catch (IOException e) {
            LOGGER.error(e);
        }

        return SKIP_BODY;
    }
}

