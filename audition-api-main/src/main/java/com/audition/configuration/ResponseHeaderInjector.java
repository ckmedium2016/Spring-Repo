package com.audition.configuration;

import io.micrometer.tracing.CurrentTraceContext;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Optional;

@Component
public class ResponseHeaderInjector implements Filter {

    //private static final AuditionLogger logger = (AuditionLogger) LoggerFactory.getLogger(ResponseHeaderInjector.class);
    @Autowired
    private Tracer tracer;
    // logger.info("Hello with Sleuth");
    // Span span = tracer.currentSpan();
    // Changes to inject openTelemetry trace and span Ids in the response headers.
    private static final String TRACE_ID_HEADER_NAME = "traceparent";
    public static final String DEFAULT = "00";

    //The traceparent will be available in the response header of every response
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
        FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        if (!response.getHeaderNames().contains(TRACE_ID_HEADER_NAME)) {
            if (Optional.of(tracer).map(Tracer::currentTraceContext).map(CurrentTraceContext::context).isEmpty()) {
                chain.doFilter(req, res);
                return;
            }
            var context = tracer.currentTraceContext().context();
            var traceId = context.traceId();
            var spanId = context.spanId();
            var traceparent = DEFAULT + ":traceId-" + traceId + ":spanId-" + spanId + "-" + DEFAULT;
            response.setHeader(TRACE_ID_HEADER_NAME, traceparent);
        }
        chain.doFilter(req, res);
    }

}

