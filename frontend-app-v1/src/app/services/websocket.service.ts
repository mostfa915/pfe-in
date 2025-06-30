// websocket.service.ts
import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Client, IMessage } from '@stomp/stompjs';
import { BehaviorSubject, Observable } from 'rxjs';
import { NotificationMessage } from '../models/NotificationMessage.model';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private client!: Client;
    private connectionStatus = new BehaviorSubject<boolean>(false);
  private notificationsSubject = new BehaviorSubject<NotificationMessage[]>([]);
  public notifications$ = this.notificationsSubject.asObservable();

  constructor() {
    this.initializeConnection();
  }

  private initializeConnection(): void {
    this.client = new Client({
      brokerURL: 'ws://localhost:8081/ws',
      connectHeaders: {
        Authorization: `Bearer ${localStorage.getItem('token')}`
      },
      debug: (str) => console.log(str),
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000
    });

    this.client.onConnect = () => {
      this.client.subscribe('/user/queue/notifications', (message: IMessage) => {
        const notification: NotificationMessage = JSON.parse(message.body);
        this.addNotification(notification);
      });
    };

    this.client.activate();
  }

  private addNotification(notification: NotificationMessage): void {
    const currentNotifications = this.notificationsSubject.value;
    this.notificationsSubject.next([notification, ...currentNotifications]);
  }

  public disconnect(): void {
    this.client?.deactivate();
  }
   public Connect(): Observable<boolean> {
    return this.connectionStatus.asObservable();
  }

}