package com.seanxiaao.springbootwithgraphql.context;

import com.netflix.graphql.dgs.context.DgsCustomContextBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyContextBuilder implements DgsCustomContextBuilder<MyContextBuilder.MyContext> {

    @Override
    public MyContext build() {
        return new MyContext();
    }

    public static class MyContext {
        private final String customState = "Custom state!";

        public String getCustomState() {
            return customState;
        }
    }

}


