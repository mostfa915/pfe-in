export interface NotificationMessage {
  id: string;
  type: 'INFO' | 'ALERT' | 'MESSAGE';
  title: string;
  content: string;
  timestamp: Date;
  isRead: boolean;
  sender?: {
    id: string;
    name: string;
    avatar?: string;
  };
  metadata?: any;
}