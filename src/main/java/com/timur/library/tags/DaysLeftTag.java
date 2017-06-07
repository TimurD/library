package com.timur.library.tags;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.jsp.tagext.TagSupport;

import com.timur.library.managers.Message;
import org.apache.log4j.Logger;
/**
 * Created by timur on 04.06.2017.
 */
public class DaysLeftTag extends TagSupport {

    public static final Logger LOGGER = Logger.getLogger(DaysLeftTag.class);

    private Date returnDate;

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int doStartTag() {
        if(returnDate==null){
            return SKIP_BODY;
        }
        String locale = (String) pageContext.getSession().getAttribute("locale");
        String content;

        long days = (TimeUnit.MILLISECONDS.toDays(returnDate.getTime() - new Date().getTime()));

        content =days>=0 ? days+1L + Message.getInstance(locale).getString(Message.DAYS_LEFT) : -days + Message.getInstance(locale).getString(Message.DAYS_OVERDUE);

        try {
            pageContext.getOut().write(content);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
        }

        return SKIP_BODY;
    }
}

