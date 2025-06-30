
import { Component, OnDestroy, OnInit } from '@angular/core';
import { WebSocketService } from '../../services/websocket.service';
import { NotificationMessage } from '../../models/NotificationMessage.model';

@Component({
  selector: 'app-notification-list',
  templateUrl: './notification-list-component.html',
  styleUrls: ['./notification-list-component.css']
})
export class NotificationListComponent implements OnInit, OnDestroy {
  notifications: NotificationMessage[] = [];
  unreadCount = 0;

  constructor(private wsService: WebSocketService) {}

  ngOnInit(): void {
    this.wsService.notifications$.subscribe(notifications => {
      this.notifications = notifications;
      this.unreadCount = notifications.filter(n => !n.isRead).length;
    });
  }

  markAsRead(notification: NotificationMessage): void {
    notification.isRead = true;
    this.unreadCount--;
    // Call your API to mark as read on backend
  }

  ngOnDestroy(): void {
    this.wsService.disconnect();
  }
}