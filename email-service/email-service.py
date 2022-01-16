import string
import redis
import json
import os
import smtplib
from email.message import EmailMessage


def send_email(content: string, from_where: string, to_where: string, subject: string):
    em = EmailMessage()
    em['Subject'] = subject
    em['From'] = from_where
    em['To'] = to_where
    em.set_content(content)

    smtp_host = os.getenv('SMTP_SERVER_HOST', 'smtp-server')
    smtp = smtplib.SMTP(smtp_host, 1025)
    smtp.send_message(em)
    smtp.quit()


if __name__ == '__main__':
    redis_host = os.getenv('REDIS_HOST', 'queue-service')
    r = redis.Redis(host=redis_host, port=6379, db=0)

    while True:
        try:
            message = json.loads(r.blpop('transactional-emails')[1])
            send_email(
                message['content'],
                message['from'],
                message['to'],
                message['subject']
            )
            print('Email sent successfully.')
        except Exception:
            print('Failed to send the email.')
