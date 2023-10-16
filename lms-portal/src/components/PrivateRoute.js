import React from 'react';
import { useCookies } from 'react-cookie';
import Constants from "Constants";

const PrivateRoute = (roleName, props) => {
    const [cookies] = useCookies([Constants.COOKIE.LMS_TOKEN, Constants.COOKIE.LMS_ROLE, Constants.COOKIE.LMS_DATA]);

    const isAuth = cookies[Constants.COOKIE.LMS_TOKEN];
    const role = cookies[Constants.COOKIE.LMS_ROLE];
    const data = cookies[Constants.COOKIE.LMS_DATA];

    const renderChildren = () => {
        return React.Children.map(props.children, (child) => {
            return React.cloneElement(child, {
                currentUser: data
            });
        });
    };

    if (roleName)
        return isAuth && role && role.toLowerCase() === roleName.toLowerCase() ? <>{renderChildren()}</> : null;
    else
        return isAuth && role ? <>{renderChildren()}</> : null;
}

export const PrivateAnyRoute = (childNode) => {
    return PrivateRoute(null, childNode);
}

export const PrivateAdminRoute = (childNode) => {
    return PrivateRoute(Constants.ROLE.ADMIN, childNode);
}

export const PrivateLibrarianRoute = (childNode) => {
    return PrivateRoute(Constants.ROLE.LIBRARIAN, childNode);
}

export const PrivateStudentRoute = (childNode) => {
    return PrivateRoute(Constants.ROLE.USER, childNode);
}