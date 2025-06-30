import { Component, OnInit, OnDestroy } from '@angular/core';
import { WebSocketService } from '../../services/websocket.service';
import { AuthService } from '../../services/auth.service';
import { CommunicationService } from '../../services/communication.service';
import { Subscription } from 'rxjs';
import { Communication } from 'src/app/models/communication.model';
  import { AfterViewChecked, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-communication',
  templateUrl: './communication.component.html',
  styleUrls: ['./communication.component.css']
})
export class CommunicationComponent implements OnInit, OnDestroy {
   @ViewChild('scrollContainer') private scrollContainer!: ElementRef;
  private wsSubscription!: Subscription;
 communications: Communication[] = [];
  unreadCount: number = 0;
  newMessage: string = '';
  currentUserId: number | null = null;
  constructor(
    private wsService: WebSocketService,
    private authService: AuthService,
      private communicationService: CommunicationService
  ) {}

  ngOnInit(): void {
 


console.log(localStorage.getItem('jwt_token')) ;
   const user = this.authService.getCurrentUser();
   console.log(user);
if (user && user.id) {
  this.currentUserId = user.id;
  this.wsService.Connect();
  this.wsSubscription = this.wsService.notifications$.subscribe(notification => {
    this.loadCommunications();
    this.loadUnreadCount();
  });
}
  }


 



   /*loadCommunications(): void {
    if (!this.currentUserId) return;
    
    this.communicationService.getUserCommunications(this.currentUserId)
      .subscribe({
        next: (comms) => {
          this.communications = comms;
        },
        error: (err) => {
          console.error('Failed to load communications:', err);
        }
      });
      console.log(this.communications);
  }
  */
loadCommunications(): void {
    if (!this.currentUserId) return;
    
    this.communicationService.getAll()
  .subscribe({
        next: (comms) => {
          this.communications = comms;
          console.log(this.communications);

        },
        error: (err) => {
          console.error('Failed to load communications:', err);
        }
      });
     console.log(this.communications);
    }
  loadUnreadCount(): void {
    if (!this.currentUserId) return;
    
    this.communicationService.getUnreadCount(this.currentUserId)
      .subscribe({
        next: (count) => {
          this.unreadCount = count;
        },
        error: (err) => {
          console.error('Failed to load unread count:', err);
        }
      });
  }
  markAsRead(communication: Communication): void {
  if (!communication.isRead && communication.id) {
    this.communicationService.markAsRead(communication.id)
      .subscribe({
        next: () => {
          communication.isRead = true;
          this.unreadCount = Math.max(0, this.unreadCount - 1);
        },
        error: (err) => {
          console.error('Failed to mark as read:', err);
        }
      });
  }
}


  ngOnDestroy(): void {
    this.wsSubscription?.unsubscribe();
    this.wsService.disconnect();
  }
sendMessage(): void {
  console.log("Méthode sendMessage appelée");

  if (!this.newMessage.trim() || !this.currentUserId) return;

  const newComm = {
    content: this.newMessage,
    senderId: this.currentUserId,
    receiverId: 1,
    isRead: false
  };

  this.communicationService.createCommunication(newComm,this.currentUserId).subscribe({
    next: () => {
      this.newMessage = '';
      this.loadCommunications();
      this.loadUnreadCount();
      console.log("Message envoyé avec succès !");
    },
    error: (err) => {
      console.error('Erreur lors de l\'envoi du message:', err);
    }
  });
}


  // ... reste du code existant
}