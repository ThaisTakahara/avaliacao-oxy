import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { Pessoa } from '../pessoa.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-pessoa-create',
  standalone: true,
  imports: [
    MatButtonModule,
    RouterOutlet, 
    RouterLink, 
    RouterLinkActive],
  templateUrl: './pessoa-create.component.html',
  styleUrl: './pessoa-create.component.css'
})
export class PessoaCreateComponent {
  baseUrl = "http://localhost:8080/api/pessoa";

  constructor(private snackBar: MatSnackBar, private http: HttpClient) { }

  pessoa: Pessoa = {
    nome: 'Thais',
    dataNascimento: new Date,
    cpf: '',
    telefone: '',
    idade: 1
  };

  createPessoa(): void {
    this.create(this.pessoa).subscribe(() => {
      this.snackBar.open('Operação Realizada com Sucesso!', 'X', {
        duration: 3000,
        horizontalPosition: "right",
        verticalPosition: "top"
      });
    });
  }
  
  private create(pessoa: Pessoa): Observable<Pessoa> {
    return this.http.post<Pessoa>(this.baseUrl, pessoa);
  }

}
