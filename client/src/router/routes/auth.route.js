export default [
    {
        path: '/auth',
        component: () => import('@/views/auth/dashboard'),
        children: [
            {
                name: 'authLogin',
                path: 'login',
                component: () => import('@/views/auth/login'),
            },
            {
                name: 'authLogout',
                path: 'logout',
                component: () => import('@/views/auth/logout'),
            },
        ]
    }
]