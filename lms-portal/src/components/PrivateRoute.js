import { useCookies } from 'react-cookie';

export const PrivateRoute = ({ children }) => {
    const [cookie, setCookie, removeCookie] = useCookies(['LMS_TOKEN']);

    return cookie ? children : null;
}