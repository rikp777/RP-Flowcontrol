export default [
    {
        path: '/production',
        name: 'productionDashboard',
        component: () => import('@/views/production/dashboard'),
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