// @ts-ignore
import { RouteRecordRaw } from "vue-router";

import Dashboard from "@/views/Dashboard.vue";
import MainLayout from "@/views/MainLayout.vue";
import Ticket from "@/views/production/Ticket.vue";
import Login from "@/components/auth/Login.vue";
import Logout from "@/components/auth/Logout.vue";

const routes: Array<RouteRecordRaw> = [
    // {
    //     path: "/:any*",
    //     redirect: {
    //         name: "Dashboard"
    //     }
    // },
    {
        path: "/",
        redirect: { name: "Dashboard" },
        component: MainLayout,
        children: [
            {
                path: "/dashboard",
                name: "Dashboard",
                component: Dashboard
            },
            {
                path: "/production",
                name: "Production",
                component: Ticket
            }
        ]
    },
    {
        path: "/login",
        component: Login
    },
    {
        path: "/Logout",
        component: Logout
    }
]

export default routes;