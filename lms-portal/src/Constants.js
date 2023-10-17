const env = process.env;

const Constants = {
    BASE_URL: env.REACT_APP_BASE_URL,
    ADMIN_URL: env.REACT_APP_ADMIN_URL,
    AUTH_URL: env.REACT_APP_AUTH_URL,
    BOOK_URL: env.REACT_APP_BOOK_URL,
    ROLE: {
        ADMIN: 'ADMIN',
        LIBRARIAN: 'LIBRARIAN',
        USER: 'USER',
    },
    ROLE_NAME: ['ADMIN', 'LIBRARIAN', 'USER'],
    COOKIE: {
        LMS_DATA: 'LMS_DATA',
        LMS_TOKEN: 'LMS_TOKEN',
        LMS_ROLE: 'LMS_ROLE',
        LMS_USER: 'LMS_USER'
    }
};

export default Constants;