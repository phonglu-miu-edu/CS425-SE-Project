import { useCookies } from 'react-cookie';
import Constants from "Constants";

const PrivateRoute = (roleName, { children }) => {
    const [cookies] = useCookies([Constants.COOKIE.LMS_TOKEN, Constants.COOKIE.LMS_ROLE]);

    const isAuth = cookies[Constants.COOKIE.LMS_TOKEN];
    const role = cookies[Constants.COOKIE.LMS_ROLE];

    if (roleName)
        return isAuth && role && role.toLowerCase() === roleName.toLowerCase() ? children : null;
    else
        return isAuth && role ? children : null;
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