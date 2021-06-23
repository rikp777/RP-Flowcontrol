export default [
    {
        path: '/production',
        name: 'productionDashboard',
        component: () => import('@/views/production/dashboard'),
        children: [
            {
                name: 'createTicket',
                path: '/ticket',
                component: () => import('@/views/production/ticket/ticketDashboard'),
            },
        ]
    }
]