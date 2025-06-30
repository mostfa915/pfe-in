import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommunicationListComponent } from './components/communication-list/communication-list.component';
import { CoproprietaireListComponent } from './components/coproprietaire-list/coproprietaire-list.component';
import { CoproprietaireFormComponent } from './components/coproprietaire-form/coproprietaire-form.component';
import { CoproprietaireEditComponent } from './components/coproprietaire-edit/coproprietaire-edit.component';
import { DocumentListComponent } from './components/document-list/document-list.component';
import { AjouterDocumentComponent } from './ajouter-document/ajouter-document.component';
import { DocumentDetailsComponent } from './document-details/document-details.component';
import { AjouterIncidentComponent } from './components/ajouter-incident/ajouter-incident.component';
import { IncidentListComponent } from './incident-list/incident-list.component';
import { EditIncidentComponent } from './incidents/edit-incident/edit-incident.component';
import { PrestataireComponent } from './components/prestataire/prestataire.component';
import { PrestataireListComponent } from './components/prestataire-list/prestataire-list.component';
import { PrestataireEditComponent } from './components/prestataire-edit/prestataire-edit.component';
import { DocumentEditComponent } from './components/document-edit/document-edit.component';
import { SyndicListComponent } from './components/syndic-list/syndic-list.component';
import { SyndicFormComponent } from './components/syndic-form/syndic-form.component';
import { LoginComponent } from './components/login/login.component';
import { AppComponent } from './app.component';
import { AuthGuard } from './auth.guard';
import { NotificationListComponent } from './components/notification-list-component/notification-list-component.component';
import { CommunicationComponent } from './components/communication/communication.component';


const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'home', component: AppComponent, canActivate: [AuthGuard] }, // Protégé
  { path: 'communications', component: CommunicationListComponent, canActivate: [AuthGuard] },
  { path: 'coproprietaires', component: CoproprietaireListComponent, canActivate: [AuthGuard] },
  { path: 'coproprietaires/nouveau', component: CoproprietaireFormComponent, canActivate: [AuthGuard] },
  { path: 'coproprietaires/edit/:id', component: CoproprietaireEditComponent, canActivate: [AuthGuard] },
  { path: 'documents', component: DocumentListComponent, canActivate: [AuthGuard] },
  { path: 'documents/ajouter', component: AjouterDocumentComponent, canActivate: [AuthGuard] },
  { path: 'documents/:id', component: DocumentDetailsComponent, canActivate: [AuthGuard] },
  { path: 'ajouter-incident', component: AjouterIncidentComponent, canActivate: [AuthGuard] },
  { path: 'incidents', component: IncidentListComponent, canActivate: [AuthGuard] },
  { path: 'incidents/edit/:id', component: EditIncidentComponent, canActivate: [AuthGuard] },
  { path: 'prestataires', component: PrestataireComponent, canActivate: [AuthGuard] },
  { path: 'prestatairesl', component: PrestataireListComponent, canActivate: [AuthGuard] },
  { path: 'prestataires/edit/:id', component: PrestataireEditComponent, canActivate: [AuthGuard] },
  { path: 'documents/edit/:id', component: DocumentEditComponent, canActivate: [AuthGuard] },
  { path: 'syndics', component: SyndicListComponent, canActivate: [AuthGuard] },
  { path: 'syndics/edit/:id', component: SyndicFormComponent, canActivate: [AuthGuard] },
  { path: 'syndics/new', component: SyndicFormComponent },
  { path: 'com', component: CommunicationComponent, canActivate: [AuthGuard] },
  { path: 'not', component: NotificationListComponent, canActivate: [AuthGuard] },

  { path: '**', redirectTo: '' } // Redirige vers login si route inconnue
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
