// communication.model.ts
import { User } from './user.model';

export interface Communication {
  id: number;
  content: string;
  dateCreation: Date;
  isRead: boolean;
  sender: User;
  receiver: User;
  type: 'SMS' | 'EMAIL' | 'NOTIFICATION';
  
  senderId: number;
  receiverId: number;
}