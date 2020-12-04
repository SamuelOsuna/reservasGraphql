package com.samuel.reservasServidorGraphql.errors;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class CustomError extends RuntimeException implements GraphQLError {

    public CustomError(String message) {
        super(message,null,false,false);

    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }

}
