<?php

namespace App\Entity;

use App\Repository\CartebRepository;
use Doctrine\ORM\Mapping as ORM;
#use PHPMailer\PHPMailer\PHPMailer;
#use PHPMailer\PHPMailer\Exception;
#require __DIR__ .'../PHPMailer-master/src/PHPMailer.php';
#require __DIR__ .'../PHPMailer-master/src/SMTP.php';
#require __DIR__ .'../PHPMailer-master/src/Exception.php';

/**
 * @ORM\Entity(repositoryClass=CartebRepository::class)
 */
class Carteb
{
    /**
     * @ORM\Id
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     */
    private $numcarte;

    /**
     * @ORM\Column(type="integer")
     */
    private $ccv;

    /**
     * @ORM\Column(type="string", length=30)
     */
    private $nom;

    /**
     * @ORM\Column(type="date")
     */
    private $dateexp;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $email;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNumcarte(): ?int
    {
        return $this->numcarte;
    }

    public function setNumcarte(int $numcarte): self
    {
        $this->numcarte = $numcarte;

        return $this;
    }

    public function setId(int $id): self
    {
        $this->id = $id;

        return $this;
    }

    public function getCcv(): ?int
    {
        return $this->ccv;
    }

    public function setCcv(int $ccv): self
    {
        $this->ccv = $ccv;

        return $this;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getDateexp(): ?\DateTimeInterface
    {
        return $this->dateexp;
    }

    public function setDateexp(\DateTimeInterface $dateexp): self
    {
        $this->dateexp = $dateexp;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }
    public function email ($contenu , $adress)  {
        $mail = new PHPMailer();
        $mail->IsSMTP();
        $mail->Mailer = "smtp";
        $mail->SMTPDebug  = 1;
        $mail->SMTPAuth   = TRUE;
        $mail->SMTPSecure = "tls";
        $mail->Port       = 587;
        $mail->Host       = "smtp.gmail.com";
        $mail->Username   = "yacine.bouguerra@esprit.tn";
        $mail->Password   = "172839456aaa";
        $mail->IsHTML(true);
        $mail->AddAddress($adress, "recipient-name");
        $mail->SetFrom("pidevMstore@gmail.com", "Pidev");
        $mail->AddReplyTo("test", "reply-to-name");
        $mail->AddCC("cc-recipient-email@domain", "cc-recipient-name");
        $mail->Subject = "Test is Test Email sent via Gmail SMTP Server using PHP Mailer";
        $content = "<b> $contenu </b>";
        $mail->MsgHTML($content);
        if(!$mail->Send()) {

            var_dump($mail);
        } else {

        }
    }
}
