import { Routes } from '@angular/router';

import { HomeComponent } from './views/home/home.component';
import { PessoaCrudComponent } from './views/pessoa-crud/pessoa-crud.component';
import { PessoaCreateComponent } from './components/pessoa/pessoa-create/pessoa-create.component';

export const routes: Routes = [
    {
        path: "",
        component: HomeComponent
    },
    {
        path: "pessoa",
        component: PessoaCrudComponent
    },
    {
        path: 'pessoa/create',
        component: PessoaCreateComponent
    }
];